package com.dl7.shopping.module.fragment.phonecart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.PhoneCUListViewAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.PhoneCartFragmentListBean;
import com.dl7.shopping.module.activity.home.phonecart.PhoneCartDetailActivity;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 联通
 * Created by MC.Zeng on 2017-07-28.
 */

public class PhoneCUFragment extends Fragment {

    private String longitude;
    private String latitude;
    private String card_type;
    private List<PhoneCartFragmentListBean.DataBean> mList=new ArrayList<>();
    private ListView listView;
    private PhoneCUListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phonecart_listview, container, false);


        SharedPreferences sp = getActivity().getSharedPreferences("flag",getActivity().MODE_PRIVATE);

        longitude = sp.getString("longitude", "");
        latitude = sp.getString("latitude", "");
        //默认是联通
        card_type = "UNICOM";
        listView = (ListView) view.findViewById(R.id.lv_phone_cart_fragment);
        initListData();

        adapter = new PhoneCUListViewAdapter(mList,getContext());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sp1 = getActivity().getSharedPreferences("flag", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp1.edit();
                editor1.putString("goodsId", mList.get(position).getId());
                editor1.commit();
                Intent intent=new Intent(getContext(),PhoneCartDetailActivity.class);
                intent.putExtra("top", "ListView");
                intent.putExtra("goodsId",mList.get(position).getId());
                intent.putExtra("bottom", "WebView");
                intent.putExtra("province_id",mList.get(position).getProvince_id());
                intent.putExtra("city_id",mList.get(position).getCity_id());
                intent.putExtra("store_id",mList.get(position).getStore_id());
                intent.putExtra("card_type",mList.get(position).getCard_type());
                startActivity(intent);
            }
        });

        return view;
    }

    //获取列表数据
    private void initListData() {

        Log.i("initListData: ",latitude );
        Log.i("initListData: ",longitude );
        Log.i("initListData: ",card_type );
        OkGo.<String>post(URL.PHONELIST_URL)
                .params("longitude",longitude)
                .params("latitude",latitude)
                .params("card_type",card_type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess111: ",json );

                        Gson gson=new Gson();
                        PhoneCartFragmentListBean phoneCartFragmentListBean = gson.fromJson(json, PhoneCartFragmentListBean.class);
                        List<PhoneCartFragmentListBean.DataBean> dataBeen=new ArrayList<PhoneCartFragmentListBean.DataBean>();
                        mList.clear();
                        mList.addAll(phoneCartFragmentListBean.getData());
                        adapter.notifyDataSetChanged();

                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this))//加上判断
//            EventBus.getDefault().unregister(this);
    }
}
