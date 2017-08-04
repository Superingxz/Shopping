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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

                        try {
                            Gson gson=new Gson();
                            PhoneCartFragmentListBean phoneCartFragmentListBean = gson.fromJson(json, PhoneCartFragmentListBean.class);
                            List<PhoneCartFragmentListBean.DataBean> dataBeen=new ArrayList<PhoneCartFragmentListBean.DataBean>();

                            JSONObject j1=new JSONObject(json);
                            JSONArray data = j1.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject dataObj = data.getJSONObject(i);
                                phoneCartFragmentListBean.getData().get(i).setCard_type(dataObj.getString("card_type"));
                                phoneCartFragmentListBean.getData().get(i).setGoods_id(dataObj.getString("goods_id"));
                                phoneCartFragmentListBean.getData().get(i).setId(dataObj.getString("id"));
                                phoneCartFragmentListBean.getData().get(i).setCity_id(dataObj.getString("city_id"));
                                phoneCartFragmentListBean.getData().get(i).setCountry_id(dataObj.getString("country_id"));
                                phoneCartFragmentListBean.getData().get(i).setDiscount(dataObj.getInt("discount"));
                                phoneCartFragmentListBean.getData().get(i).setDescribes(dataObj.getString("describes"));
                                phoneCartFragmentListBean.getData().get(i).setPresent_price(dataObj.getInt("present_price"));
                                phoneCartFragmentListBean.getData().get(i).setOriginal_price(dataObj.getInt("original_price"));
                                phoneCartFragmentListBean.getData().get(i).setDetails(dataObj.getString("details"));
                                phoneCartFragmentListBean.getData().get(i).setGoodsName(dataObj.getString("goodsName"));
                                phoneCartFragmentListBean.getData().get(i).setStoreName(dataObj.getString("storeName"));
                                phoneCartFragmentListBean.getData().get(i).setStore_id(dataObj.getString("store_id"));
                                phoneCartFragmentListBean.getData().get(i).setSpec(dataObj.getInt("spec"));
                                phoneCartFragmentListBean.getData().get(i).setProvince_id(dataObj.getString("province_id"));
                                phoneCartFragmentListBean.getData().get(i).setStatus(dataObj.getString("status"));

                                dataBeen.add(phoneCartFragmentListBean.getData().get(i));
                            }
                            mList.clear();
                            mList.addAll(dataBeen);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
