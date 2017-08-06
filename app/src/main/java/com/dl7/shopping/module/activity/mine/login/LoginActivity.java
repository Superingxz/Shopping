package com.dl7.shopping.module.activity.mine.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.activity.mine.login.presenter.LoginPresenter;
import com.dl7.shopping.module.activity.mine.login.view.ILoginView;
import com.dl7.shopping.module.activity.mysetting.findpassword.FindPasswordActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.FirstEvent;
import com.dl7.shopping.rxbus.event.FourEvent;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by MC.Zeng on 2017-07-01.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.login_back)
    TextView back;
    @BindView(R.id.edit_login_account)
    EditText etPhone;
    @BindView(R.id.edit_login_password)
    EditText etPassword;
    @BindView(R.id.iv_login_remember)
    ImageView ivRemenber;
    @BindView(R.id.ll_login_remember_password)
    LinearLayout llRemenberPassword;
    @BindView(R.id.tv_find_password)
    TextView findPassword;
    @BindView(R.id.btn_login)
    Button login;
    private Typeface iconFont;
    private boolean isVision = true;//是否显示密码
    private String account;//EditText获得的帐号
    private String password;
    private boolean isUpdate = false;
    private String type;


    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
//        EventBus.getDefault().register(this);
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        back.setTypeface(iconFont);
        /*llRemenberPassword.setTag("unremember");
        llRemenberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("unremember".equals(llRemenberPassword.getTag())) {
                    //如果是没有记住密码状态就变成记住密码状态
                    ivRemenber.setImageResource(R.mipmap.square_frame2);
                    llRemenberPassword.setTag("remember");
                } else {
                    //如果是记住密码状态就变成没有记住密码状态
                    ivRemenber.setImageResource(R.mipmap.square_frame1);
                    llRemenberPassword.setTag("unremember");
                }
            }
        });*/

        final Drawable[] drawables = etPassword.getCompoundDrawables();
        final int eyeWidth = drawables[2].getBounds().width();// 眼睛图标的宽度
        final Drawable drawableEyeOpen = getResources().getDrawable(R.mipmap.eyes2);
        drawableEyeOpen.setBounds(drawables[2].getBounds());

        findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPasswordActivity.class);
                startActivity(intent);
            }
        });

        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // getWidth,getHeight必须在这里处理
                    float et_pwdMinX = v.getWidth() - eyeWidth - etPassword.getPaddingRight();
                    float et_pwdMaxX = v.getWidth();
                    float et_pwdMinY = 0;
                    float et_pwdMaxY = v.getHeight();
                    float x = event.getX();
                    float y = event.getY();

                    if (x < et_pwdMaxX && x > et_pwdMinX && y > et_pwdMinY && y < et_pwdMaxY) {
                        isVision = !isVision;

                        if (isVision) {
                            etPassword.setCompoundDrawables(drawables[0], drawables[3], drawables[2], null);

                            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            etPassword.setCompoundDrawables(drawables[0], drawables[3], drawableEyeOpen, null);
                            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    }
                }
                return false;
            }
        });

        Intent intent = getIntent();
        String intentAccount = intent.getStringExtra("account");
        String intentPassword = intent.getStringExtra("password");//注册完得到帐号密码
        type = intent.getStringExtra("type");

        if (intentAccount != null && intentPassword != null) {
            etPhone.setText(intentAccount);
            etPassword.setText(intentPassword);
        }

        //查看是否有保存的账号
        SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
        if (!TextUtils.isEmpty(sp.getString("userphone", ""))) {
            ivRemenber.setImageResource(R.mipmap.square_frame2);
            llRemenberPassword.setTag("remember");
            //有，自动填写账号密码
            login.setBackgroundColor(Color.parseColor("#35BB8A"));
            etPhone.setText(sp.getString("userphone", ""));
          //  etPassword.setText(sp.getString("password",""));
            etPassword.setText("password");
            password="password";
        }
        getEditTextPhone();//获取账号
        getEditTextPassword();//获取密码


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etPassword.getText().toString().equals("") || etPhone.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "请输入帐号和密码", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
                    //如果有保存的账号说明之前是记住密码的
                    if (!TextUtils.isEmpty(sp.getString("userphone", "")) && !isUpdate) {
                        //输入的和保存的一致
                        if (etPhone.getText().toString().equals(sp.getString("userphone", ""))) {

                            //判断是否选择了记住密码
                            if ("unremember".equals(llRemenberPassword.getTag())) {
                                //没有记住密码
                                SharedPreferences sp1 = getSharedPreferences("flag", MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sp1.edit();
                                editor1.putString("userphone", "");
                                editor1.putString("password", "");
                                editor1.putString("userid", "0");
                                editor1.putString("islogin", "false");//登录状态
                                editor1.putString("image", "");
                                editor1.putString("name", "");
                                editor1.putString("mobile", "");
                                editor1.commit();
                            }
                        } else {
                            //没有记住密码
                            initData();
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("data", sp.getString("userid", "0"));
                            editor.commit();

                        }
                    } else {
                        //输入的和保存的一致
                        if (etPhone.getText().toString().equals(sp.getString("userphone", ""))) {

                            //判断是否选择了记住密码
                            if ("unremember".equals(llRemenberPassword.getTag())) {
                                //没有记住密码
                                SharedPreferences sp1 = getSharedPreferences("flag", MODE_PRIVATE);
                                SharedPreferences.Editor editor1 = sp1.edit();
                                editor1.putString("userphone", "");
                                editor1.putString("password", "");
                                editor1.putString("userid", "0");
                                editor1.putString("islogin", "false");//登录状态
                                editor1.putString("image", "");
                                editor1.putString("name", "");
                                editor1.putString("mobile", "");
                                editor1.commit();
                            }
                        } else {
                            //没有记住密码
                            initData();
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("data", sp.getString("userid", "0"));
                            editor.commit();
                        }
                    }
                    initData();
                }
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    private void initData() {
        SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
//        if (etPhone.getText().toString().equals(sp.getString("userphone", ""))) {//判断输入的密码和保存的密码是否相同
//            password = sp.getString("password","");//相同得到保存的密码
//        }else{
//            password=etPassword.getText().toString();//不同就得到输入的密码
//        }
        if (isUpdate){
            password=etPassword.getText().toString();
        }else{
            password = sp.getString("password","");
        }
        OkGo.<String>post(URL.LOGIN_URL)
                .params("account",etPhone.getText().toString())
                .params("passwd",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        LoginActivity.this.finish();

                        try {
                            JSONObject j1=new JSONObject(json);
                            Toast.makeText(LoginActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();
                            if (j1.getString("event").equals("200")){
                                JSONObject data = j1.getJSONObject("data");
                                String member_id = data.getString("member_id");
                                String image = data.getString("image");
                                String name = data.getString("name");
                                String mobile = data.getString("mobile");
                                //保存用户id
                                SharedPreferences sp = getSharedPreferences("flag", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("data",member_id);

                                if("unremember".equals(llRemenberPassword.getTag())){
                                    //如果没有记住密码
                                    editor.putString("userphone","");
                                    editor.putString("password","");
                                    editor.putString("userid","0");
                                    editor.putString("islogin","false");//登录状态
                                    editor.putString("image","");
                                    editor.putString("name","");
                                    editor.putString("mobile","");
                                    editor.commit();

                                }else{
                                    //如果记住密码了就保存用户账号
                                    editor.putString("userphone",etPhone.getText().toString());
                                    editor.putString("userid",member_id);
                                    if (isUpdate==true){
                                        editor.putString("password",etPassword.getText().toString());
                                    }else{
                                        editor.putString("password",sp.getString("password",""));
                                    }
//                                    editor.putString("password",etPassword.getText().toString());
                                    editor.putString("islogin","true");//登录状态
                                    editor.putString("image",image);
                                    editor.putString("name",name);
                                    editor.putString("mobile",mobile);
                                    editor.commit();
                                }
                                //刷新我的页面
                                EventBus.getDefault().post(
                                        new FirstEvent(member_id));

                                //刷新订水页面
                                EventBus.getDefault().post(
                                        new FourEvent(member_id));
//                                isLogin=true;
//                                MainActivity.instance.finish();//关闭之前的首页
                                LoginOrRegisterActivity.instance.finish();
                                finish();
//                                if (type.equals("2")){
//                                    LoginOrRegisterActivity.instance.finish();
//                                    Intent intent=new Intent(LoginActivity.this,MineBalanceActivity.class);
//                                    startActivity(intent);
//                                }
//                                LoginOrRegisterActivity.instance.finish();
//                                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                                startActivity(intent);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

//                    @Override
//                    public void onError(Response<String> response) {
//                        String json = response.body().toString();
//                        Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//                        Log.i( "onError: ",json);
//                    }
                });
    }

    private void getEditTextPassword() {
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //重新写入了密码
                password = s.toString();
                isUpdate = true;
                login.setBackgroundColor(Color.parseColor("#35BB8A"));
            }
        });
    }

    private void getEditTextPhone() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (start!=before){//根据输入的帐号长度来判断是否修改了帐号，是则让密码框为空
                    etPassword.setText("");
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
                account = s + "";
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finishRefresh() {

    }
}
