package com.dl7.shopping.module.activity.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.ComboItemAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.ComboItemBean;
import com.dl7.shopping.utils.FontManager;
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
 * 第二级的套餐页面
 * Created by MC.Zeng on 2017-07-29.
 */

public class ComboItemActivity extends AppCompatActivity {

    private Typeface iconFont;
    private TextView back;
    private ListView listView;
    private String storeID;
    private String goodsID;
    private List<ComboItemBean.DataBean> mList=new ArrayList<>();
    private ComboItemAdapter adapter;
    private String addressID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        Intent intent=getIntent();
        storeID = intent.getStringExtra("storeID");
        goodsID = intent.getStringExtra("goodsID");
        addressID = intent.getStringExtra("addressID");

        back = (TextView) findViewById(R.id.tv_combo_back);
        back.setTypeface(iconFont);
        listView = (ListView) findViewById(R.id.lv_combo);


        adapter = new ComboItemAdapter(mList,this);
        initData();
        listView.setAdapter(adapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.COMBOITEM_URL)
                .params("goods_id",goodsID)
                .params("store_id",storeID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            Gson gson=new Gson();
                            ComboItemBean comboItemBean = gson.fromJson(json, ComboItemBean.class);
                            List<ComboItemBean.DataBean> dataBeen=new ArrayList<ComboItemBean.DataBean>();

                            JSONObject j1=new JSONObject(json);
                            JSONArray data = j1.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject dataObj = data.getJSONObject(i);
                                comboItemBean.getData().get(i).setDiscount(dataObj.getInt("discount"));
                                comboItemBean.getData().get(i).setGoods_id(dataObj.getString("goods_id"));
                                comboItemBean.getData().get(i).setStore_id(dataObj.getString("store_id"));
                                comboItemBean.getData().get(i).setCreate_user_id(dataObj.getString("create_user_id"));
                                comboItemBean.getData().get(i).setGoods_name(dataObj.getString("goods_name"));
                                comboItemBean.getData().get(i).setNotes(dataObj.getString("notes"));
                                comboItemBean.getData().get(i).setSort(dataObj.getInt("sort"));
                                comboItemBean.getData().get(i).setNumber(dataObj.getInt("number"));
                                comboItemBean.getData().get(i).setMoney(dataObj.getInt("money"));
                                comboItemBean.getData().get(i).setName(dataObj.getString("name"));
                                comboItemBean.getData().get(i).setStore_name(dataObj.getString("store_name"));
                                comboItemBean.getData().get(i).setId(dataObj.getString("id"));
                                comboItemBean.getData().get(i).setGoods_company(dataObj.getString("goods_company"));
                                comboItemBean.getData().get(i).setAvailable_num(dataObj.getInt("available_num"));
                                comboItemBean.getData().get(i).setAddressID(addressID);
                                Log.i("onSuccess: ",addressID );

                                dataBeen.add(comboItemBean.getData().get(i));
                            }
                            mList.addAll(dataBeen);
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
