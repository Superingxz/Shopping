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
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
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
                        Gson gson=new Gson();
                        ComboItemBean comboItemBean = gson.fromJson(json, ComboItemBean.class);
                        mList.addAll(comboItemBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
