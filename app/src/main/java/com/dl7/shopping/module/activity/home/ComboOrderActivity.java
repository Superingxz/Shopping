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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * Created by MC.Zeng on 2017-07-29.
 */

public class ComboOrderActivity extends AppCompatActivity {

    private Typeface iconFont;
    private String uid;
    private String addressID;
    private TextView back;
    private TextView title;
    private TextView num;
    private TextView tvAddress;
    private TextView money;
    private TextView tvSpecification;
    private TextView phone;
    private TextView commit;
    private String group_id;
    private CustomPopWindow popWindow;
    private String channel;
    private String total_price;
    private String store_name;
    private String goods_name;
    private String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo_order);
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
//        EventBus.getDefault().register(this);
        Intent intent=getIntent();
        group_id = intent.getStringExtra("group_id");
        addressID = intent.getStringExtra("addressID");
        uid = CommonMethod.getUid(this);
        initView();


        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow(commit);
            }
        });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.tv_combo_order_back);
        back.setTypeface(iconFont);
        title = (TextView) findViewById(R.id.tv_combo_order_name);
        num = (TextView) findViewById(R.id.tv_combo_order_num);
        tvAddress = (TextView) findViewById(R.id.tv_combo_order_address);
        money = (TextView) findViewById(R.id.tv_combo_order_money);
        tvSpecification = (TextView) findViewById(R.id.tv_combo_order_specification);
        phone = (TextView) findViewById(R.id.tv_combo_order_phone);
        commit = (TextView) findViewById(R.id.tv_commit_order_combo);

    }

    //获取订单数据
    private void initData() {
        OkGo.<String>post(URL.COMBOORDER_URL)
                .params("group_id",group_id)
                .params("address_id",addressID)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ",json );

                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            goods_name = data.getString("goods_name");
                            String address = data.getString("address");
                            quantity = data.getString("quantity");
                            total_price = data.getString("total_price");
                            String group_id = data.getString("group_id");
                            String contact = data.getString("contact");
                            String mobile = data.getString("mobile");
                            store_name = data.getString("store_name");
                            String specification = data.getString("specification");
                            String company = data.getString("company");

                            title.setText("套餐名称:"+ goods_name);
                            tvAddress.setText("地址:"+address);
                            money.setText("总价:¥ "+ total_price);
                            phone.setText("手机:"+mobile);
                            num.setText("数量:"+ quantity +company);
                            tvSpecification.setText("规格:"+specification+"/"+company);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
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
                channel ="wx";
                initThirdCommit();
                popWindow.dissmiss();
            }
        });
        //支付宝支付
        rlAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channel ="alipay";
                initThirdCommit();
                popWindow.dissmiss();
            }
        });
    }

    //提交(第三方支付)
    private void initThirdCommit() {

        OkGo.<String>post(URL.THIRDPLAY_URL)
                .params("business_type","BUY_WATERGROUP")
                .params("address_id",addressID)
                .params("group_id",group_id)
                .params("amount",total_price)
                .params("channel",channel)
                .params("subject","爱公益商城")
                .params("body","["+store_name+"]"+goods_name+"x"+quantity)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1=new JSONObject(json);
                            String message = j1.getString("message");
                            JSONObject data = j1.getJSONObject("data");

                            Pingpp.createPayment(ComboOrderActivity.this, data.toString());

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                String result = data.getExtras().getString("pay_result");
                if (result.equals("success")){
                    Intent intent=new Intent(ComboOrderActivity.this, SuccessActivity.class);
                    startActivity(intent);
                }else if(result.equals("fail")){
                    Intent intent=new Intent(ComboOrderActivity.this, DefeatedActivity.class);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
//        EventBus.getDefault().unregister(this);
    }
}
