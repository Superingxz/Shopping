package com.dl7.shopping.module.activity.home;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.play.DefeatedActivity;
import com.dl7.shopping.module.activity.play.SuccessActivity;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MC.Zeng on 2017-07-26.
 */

public class WaterOrderActivity extends AppCompatActivity {

    private TextView back;
    private Typeface iconFont;
    private TextView icon;
    private String goodsId;
    private String addressId;
    private String allNum;
    private String uid;
    private TextView name;
    private TextView tvaddress;
    private TextView phone;
    private TextView title;
    private ImageView head;
    private TextView num;
    private TextView commit;
    private String store_name;
    private String company_id;
    private String address_id;
    private String goods_id;
    private String goods_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_order);
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        uid = CommonMethod.getUid(this);
        Intent intent=getIntent();
        goodsId = intent.getStringExtra("goodsId");
        addressId = intent.getStringExtra("addressId");
        allNum = intent.getStringExtra("allNum");

        initView();

        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.WATERORDER_URL)
                .params("goods_id",goodsId)
                .params("address_id",addressId)
                .params("quantity",allNum)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i( "onSuccess: ",json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            goods_name = data.getString("goods_name");
                            String address = data.getString("address");
                            String mobile = data.getString("mobile");
                            String total_price = data.getString("total_price");
                            String contact = data.getString("contact");
                            String quantity = data.getString("quantity");
                            String image_url = data.getString("image_url");
                            company_id = data.getString("company_id");
                            address_id = data.getString("address_id");
                            goods_id = data.getString("goods_id");
                            store_name = data.getString("store_name");

                            tvaddress.setText("收货地址:  "+address);
                            name.setText("收货人:  "+contact);
                            phone.setText(mobile);
                            num.setText("X"+quantity);
                            title.setText(goods_name);
                            Glide.with(WaterOrderActivity.this).load("http://shop-img.agymall.com/water/small/" + image_url).into(head);

                            commit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    initCommit();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    //提交
    private void initCommit() {
        OkGo.<String>post(URL.WATERPLAY_URL)
                .params("business_type","BUY_WATER")
                .params("company_id",company_id)
                .params("address_id",address_id)
                .params("goods_id",goods_id)
                .params("amount","0")
                .params("quantity",allNum)
                .params("channel","water")
                .params("subject",store_name)
                .params("body","["+store_name+"]"+goods_name+"x"+allNum)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess777: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            String message = j1.getString("message");
                            if (message.equals("加载成功")){
                                Intent intent=new Intent(WaterOrderActivity.this, SuccessActivity.class);
                                startActivity(intent);

                            }else{
                                Intent intent=new Intent(WaterOrderActivity.this, DefeatedActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.tv_water_order_back);
        back.setTypeface(iconFont);
        icon = (TextView) findViewById(R.id.tv_water_order_icon);
        icon.setTypeface(iconFont);
        name = (TextView) findViewById(R.id.tv_water_order_name);
        tvaddress = (TextView) findViewById(R.id.tv_water_order_address);
        phone = (TextView) findViewById(R.id.tv_water_order_phone);
        title = (TextView) findViewById(R.id.tv_water_order_title);
        head = (ImageView) findViewById(R.id.img_water_order_head);
        num = (TextView) findViewById(R.id.tv_water_order_num);
        commit = (TextView) findViewById(R.id.tv_commit_order_water);
    }
}
