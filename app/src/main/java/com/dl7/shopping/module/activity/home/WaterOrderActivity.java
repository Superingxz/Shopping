package com.dl7.shopping.module.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.play.DefeatedActivity;
import com.dl7.shopping.module.activity.play.SuccessActivity;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.example.zhouwei.library.CustomPopWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.pingplusplus.android.Pingpp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

/**
 * 订单确认页面
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
    private String time;
    private int reserve_sort;
    private TextView tvTime;
    private TextView tvMoney;
    private String channel;
    private String money;
    private CustomPopWindow popWindow;
    private String playmethod;
    private String business_type;
    private TextView tvBucket;
    private String buy_type="";
    private String bucket;
    private String totalmoney;
    private String store_id;
    private String business_type1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_order);
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        uid = CommonMethod.getUid(this);
        Intent intent=getIntent();
        business_type = intent.getStringExtra("business_type");

        goodsId = intent.getStringExtra("goodsId");
        Log.i("onCreate111: ",goodsId );
        addressId = intent.getStringExtra("addressId");
        allNum = intent.getStringExtra("allNum");
        time = intent.getStringExtra("time");
        money = intent.getStringExtra("money");
        playmethod = intent.getStringExtra("playmethod");
        store_id = intent.getStringExtra("store_id");
        business_type1 = intent.getStringExtra("business_type1");

        initView();
        if (!intent.getStringExtra("bucket").equals("")){
            buy_type = intent.getStringExtra("bucket");
            tvBucket.setVisibility(View.VISIBLE);
        }
        if (!intent.getStringExtra("reserve_sort").equals("")){
            reserve_sort = Integer.parseInt(intent.getStringExtra("reserve_sort"));
        }
        channel="water";//默认余水支付

        Log.i("onCreate: ",time );
        Log.i("onCreate: ",reserve_sort+"" );


//        //判断是否为桶的购买
//        if (business_type1.equals("BUY_BARREL")){
//
//        }else{
//            tvBucket.setVisibility(View.GONE);
//        }

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
        Log.i("initData: ", business_type);
        Log.i("initData: ", goodsId);
        Log.i("initData: ", store_id);
        Log.i("initData: ", reserve_sort+"");
        Log.i("initData: ", time+"");
        OkGo.<String>post(URL.WATERORDER_URL)
                .params("business_type",business_type)
                .params("goods_id",goodsId)
                .params("address_id",addressId)
                .params("quantity",allNum)
                .params("member_id",uid)
                .params("reserve_sort",reserve_sort)
                .params("reserve_time",time)
                .params("store_id",store_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i( "onSuccess111: ",json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            goods_name = data.getString("goods_name");
                            String address = data.getString("address");
                            String mobile = data.getString("mobile");
                            int total_price = data.getInt("total_price");
                            totalmoney=total_price+"";
                            Double totalPrice = Double.valueOf(Integer.parseInt(total_price+""));
                            String contact = data.getString("contact");
                            String quantity = data.getString("quantity");
                            String image_url = data.getString("image_url");
                            company_id = data.getString("company_id");
                            address_id = data.getString("address_id");
                            goods_id = data.getString("goods_id");
                            Log.i("onSuccess111: ",totalmoney );
                            store_name = data.getString("store_name");
                            String reserve_time = data.getString("reserve_time");
                            tvaddress.setText("收货地址:  "+address);
                            name.setText("收货人:  "+contact);
                            phone.setText(mobile);
                            num.setText("X"+quantity);

                            DecimalFormat df   =new   java.text.DecimalFormat("#.00");
                            tvMoney.setVisibility(View.VISIBLE);
                            tvMoney.setText("¥ "+df.format(totalPrice/100));
                            title.setText(goods_name);
                            if (reserve_time.equals("")){
                                tvTime.setVisibility(View.GONE);
                            }else{
                                tvTime.setVisibility(View.VISIBLE);
                                tvTime.setText("配送时间:  "+reserve_time);
                            }

                            Glide.with(WaterOrderActivity.this).load("http://shop-img.agymall.com/water/small/" + image_url).into(head);

                            commit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //判断支付方式
                                    if (playmethod.equals("water")){
                                        initCommit();
                                    }else if (playmethod.equals("third")){
                                        initPopupWindow(commit);
                                    }
//                                    initCommit();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //提交(余水支付)
    private void initCommit() {

        OkGo.<String>post(URL.WATERPLAY_URL)
                .params("business_type",business_type1)
                .params("company_id",company_id)
                .params("address_id",address_id)
                .params("goods_id",goods_id)
                .params("amount",money)
                .params("quantity",allNum)
                .params("resever",time)
                .params("sort",reserve_sort)
                .params("channel",channel)
                .params("subject",store_name)
                .params("body","["+store_name+"]"+goods_name+"x"+allNum)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ",json );
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

    //提交(第三方支付)
    private void initThirdCommit() {
        Log.i("initThirdCommit: ", totalmoney);
        OkGo.<String>post(URL.THIRDPLAY_URL)
                .params("business_type",business_type1)
                .params("company_id",company_id)
                .params("address_id",address_id)
                .params("goods_id",goodsId)
                .params("amount",totalmoney)
                .params("quantity",allNum)
                .params("resever",time)
                .params("sort",reserve_sort)
                .params("channel",channel)
                .params("subject",store_name)
                .params("body","["+store_name+"]"+goods_name+"x"+allNum)
                .params("member_id",uid)
                .params("buy_type",buy_type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            String message = j1.getString("message");
                            JSONObject data = j1.getJSONObject("data");

                            Pingpp.createPayment(WaterOrderActivity.this, data.toString());

//                            if (message.equals("加载成功")){
//                                Intent intent=new Intent(WaterOrderActivity.this, SuccessActivity.class);
//                                startActivity(intent);
//                            }else{
//                                Intent intent=new Intent(WaterOrderActivity.this, DefeatedActivity.class);
//                                startActivity(intent);
//                            }
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
        tvTime = (TextView) findViewById(R.id.tv_water_order_time);
        tvMoney = (TextView) findViewById(R.id.tv_water_order_money);
        tvBucket = (TextView) findViewById(R.id.tv_Water_order_bucket);
    }

    //弹框
    private void initPopupWindow(View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View popview = inflater.inflate(R.layout.activity_play_method, null);

        popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(popview)//显示的布局，还可以通过设置一个View
                //     .size(600,400) //设置显示的大小，不设置就默认包裹内容
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .setBgDarkAlpha(0.5f) // 控制亮度
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(true)//是否PopupWindow 以外触摸dissmiss
                .create()//创建PopupWindow
                .showAtLocation(view, Gravity.BOTTOM, 0, 0);//设置底部显示

        RelativeLayout rlWX = (RelativeLayout) popview.findViewById(R.id.rl_play_method_wx);
        RelativeLayout rlAlipay = (RelativeLayout) popview.findViewById(R.id.rl_play_method_alipay);

        //微信支付
        rlWX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channel="wx";
                initThirdCommit();
                popWindow.dissmiss();
            }
        });
        //支付宝支付
        rlAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channel="alipay";
                initThirdCommit();
                popWindow.dissmiss();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                String result = data.getExtras().getString("pay_result");
                if (result.equals("success")){
                    Intent intent=new Intent(WaterOrderActivity.this, SuccessActivity.class);
                    startActivity(intent);
                }else if(result.equals("fail")){
                    Intent intent=new Intent(WaterOrderActivity.this, DefeatedActivity.class);
                    startActivity(intent);
                }else if (result.equals("cancel")){
                    Toast.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
                } else if (result.equals("invalid")){
                    Toast.makeText(this, "支付插件未安装", Toast.LENGTH_SHORT).show();
                }else if (result.equals("unknown")){
                    Toast.makeText(this, "app进程异常", Toast.LENGTH_SHORT).show();
                }
            /* 处理返回值
             * "success" - 支付成功
             * "fail"    - 支付失败
             * "cancel"  - 取消支付
             * "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
             * "unknown" - app进程异常被杀死(一般是低内存状态下,app进程被杀死)
             */
//                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
//                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
//                showMsg(result, errorMsg, extraMsg);
            }
        }
    }
}