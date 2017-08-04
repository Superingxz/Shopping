package com.dl7.shopping.module.activity.home.waterstore;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.WaterStoreAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.WaterStoreBean;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.ElevenEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 选择水店页面
 * Created by MC.Zeng on 2017-07-17.
 */

public class WaterStoreActivity extends BaseActivity<WaterStorePresenter>
        implements IWaterStoreView {

    @BindView(R.id.tv_water_store_back)
    TextView back;
    @BindView(R.id.lv_water_store)
    PullToRefreshListView listView;
    @BindView(R.id.btn_water_store_confirm)
    Button confirm;
    private Typeface iconFont;
    private String uid;
    private WaterStoreAdapter adapter;
    private List<WaterStoreBean.DataBean.ListBean> mList=new ArrayList<>();
    private String address;
    public String longitude;
    public String latitude;
    private String id;
    private String storeId;
    private String str;

    private int pageNum=1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_water_store;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        uid = CommonMethod.getUid(this);

        back.setTypeface(iconFont);

        Intent intent=getIntent();
        address = intent.getStringExtra("address");
        longitude = intent.getStringExtra("longitude");
        latitude = intent.getStringExtra("latitude");
        id = intent.getStringExtra("id");
        Log.i("onCreate: ", address);
        str="1";

        adapter = new WaterStoreAdapter(mList,this);
        listView.setAdapter(adapter);

        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
                storeId = mList.get(position-1).getId();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (storeId.isEmpty()){
                    Toast.makeText(WaterStoreActivity.this, "请选择水店", Toast.LENGTH_SHORT).show();
                }else {
                    initComfire();
                }
            }
        });
    }

    //确定
    private void initComfire() {
        OkGo.<String>post(URL.UPDATESTORE_URL)
                .params("id",id)
                .params("store_id",storeId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            Toast.makeText(WaterStoreActivity.this,j1.getString("message"), Toast.LENGTH_SHORT).show();

                            EventBus.getDefault().post(
                                    new ElevenEvent("OK"));
                            finish();//结束页面
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.WATERSTORE_URL)
                .params("longitude",longitude)
                .params("latitude",latitude)
                .params("pageNum",pageNum)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i( "onSuccess: ",json);
                        Gson gson=new Gson();
                        WaterStoreBean waterStoreBean = gson.fromJson(json, WaterStoreBean.class);
                        List<WaterStoreBean.DataBean.ListBean> listBeen=new ArrayList<WaterStoreBean.DataBean.ListBean>();

                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            JSONArray list = data.getJSONArray("list");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject listObj = list.getJSONObject(i);
                                waterStoreBean.getData().getList().get(i).setAddress(listObj.getString("address"));
                                waterStoreBean.getData().getList().get(i).setName(listObj.getString("name"));
                                waterStoreBean.getData().getList().get(i).setId(listObj.getString("id"));
                                waterStoreBean.getData().getList().get(i).setDistance(listObj.getDouble("distance"));
                                listBeen.add(waterStoreBean.getData().getList().get(i));
                            }
                            mList.addAll(listBeen);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void finishRefresh() {

    }
}
