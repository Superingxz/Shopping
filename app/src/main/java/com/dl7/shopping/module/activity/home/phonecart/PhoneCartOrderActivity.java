package com.dl7.shopping.module.activity.home.phonecart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.dl7.shopping.module.activity.mysetting.address.AddressMessageActivity;
import com.dl7.shopping.module.activity.play.DefeatedActivity;
import com.dl7.shopping.module.activity.play.SuccessActivity;
import com.dl7.shopping.rxbus.event.SeventeenEvent;
import com.dl7.shopping.rxbus.event.SixteenEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.example.zhouwei.library.CustomPopWindow;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.pingplusplus.android.Pingpp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 电话卡订单
 * Created by MC.Zeng on 2017-07-29.
 */

public class PhoneCartOrderActivity extends AppCompatActivity {

    private Typeface iconFont;
    private TextView back;
    private TextView tvAddress;
    private TextView tvPhone;
    private TextView tvTitle;
    private TextView tvName;
    private RelativeLayout rlAddress;
    private TextView tvMoney;
    private TextView tvBuyPhone;
    private TextView tvCommit;
    private SharedPreferences sp;
    private String groupID;
    private String goods_id;
    private String uid;
    private String channel;
    private CustomPopWindow popWindow;
    private String deliver_method;
    private String address_id;
    private String order_id;
    private String presentPrice;
    private String storeName;
    private String cardType;
    private String contact;
    private String mobile;
    private String phoneNumber;
    private String address;
    private String goodsName;
    private String msg="";
    private String msg1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_order);
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        EventBus.getDefault().register(this);
        sp = getSharedPreferences("flag", MODE_PRIVATE);
        groupID = sp.getString("goodsId", "");
        Intent intent=getIntent();
        goods_id = intent.getStringExtra("goods_id");
        uid = CommonMethod.getUid(this);

        initView();

        initData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCreatOrder();
            }
        });

        rlAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(PhoneCartOrderActivity.this, AddressMessageActivity.class);
                intent1.putExtra("isSelect","phoneSelect");
                startActivity(intent1);
            }
        });
    }

    //创建订单
    private void initCreatOrder() {
        //判断有无更改地址
        if (!msg.equals("")){
            address_id=msg;
        }
        OkGo.<String>post(URL.PHONECREATORDER_URL)
                .params("goods_id",groupID)
                .params("card_id",goods_id)
                .params("member_id",uid)
                .params("deliver_method",deliver_method)
                .params("address_id",address_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            order_id = data.getString("order_id");
                            initPopupWindow(tvCommit);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    private void initData() {
        OkGo.<String>post(URL.PHONENUMORDER_URL)
                .params("goods_id",groupID)
                .params("card_id",goods_id)
                .params("member_id",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            JSONObject j1=new JSONObject(json);
                            JSONObject data = j1.getJSONObject("data");
                            deliver_method = data.getString("deliver_method");
                            address = data.getString("address");
                            phoneNumber = data.getString("phoneNumber");
                            presentPrice = data.getString("presentPrice");
                            contact = data.getString("contact");
                            mobile = data.getString("mobile");
                            cardType = data.getString("cardType");
                            address_id = data.getString("address_id");
                            storeName = data.getString("storeName");
                            goodsName = data.getString("goodsName");

                            tvTitle.setText("商品名称:"+ goodsName);
                            tvName.setText("收件人:"+ contact);
                            tvAddress.setText("收件地址:"+ address);
                            tvMoney.setText("价格:"+ presentPrice);
                            tvBuyPhone.setText("所选购手机号码:"+ phoneNumber);
                            tvPhone.setText("手机号:"+ mobile);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void initView() {
        back = (TextView) findViewById(R.id.tv_phone_order_back);
        back.setTypeface(iconFont);
        tvTitle = (TextView) findViewById(R.id.tv_phone_order_title);
        tvName = (TextView) findViewById(R.id.tv_phone_order_name);
        tvAddress = (TextView) findViewById(R.id.tv_phone_order_address);
        rlAddress = (RelativeLayout) findViewById(R.id.rl_phone_order_address);
        tvMoney = (TextView) findViewById(R.id.tv_phone_order_money);
        tvBuyPhone = (TextView) findViewById(R.id.tv_phone_order_buy_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone_order_phone);
        tvCommit = (TextView) findViewById(R.id.tv_commit_order_phone);
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
                .params("order_id",order_id)
                .params("amount",presentPrice)
                .params("channel",channel)
                .params("subject","爱公益商城")
                .params("body","["+storeName+"]"+goodsName+"x"+1)
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

                            Pingpp.createPayment(PhoneCartOrderActivity.this, data.toString());

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

    @Subscribe
    public void onEventMainThread(SixteenEvent event) {
        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);
    }

    @Subscribe
    public void onEventMainThread(SeventeenEvent event) {
        msg1 = event.getMsg();
        Log.i("onEventMainThread: ", msg1);
        tvAddress.setText("收货地址:"+msg1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //支付页面返回处理
        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
                String result = data.getExtras().getString("pay_result");
                if (result.equals("success")){
                    Intent intent=new Intent(PhoneCartOrderActivity.this, SuccessActivity.class);
                    startActivity(intent);
                }else if(result.equals("fail")){
                    Intent intent=new Intent(PhoneCartOrderActivity.this, DefeatedActivity.class);
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
        EventBus.getDefault().unregister(this);
    }
}
