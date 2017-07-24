package com.dl7.shopping.module.activity.mysetting.findpassword;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.CountDownButtonHelper;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 找回密码
 * Created by MC.Zeng on 2017-07-01.
 */

public class FindPasswordActivity extends BaseActivity<FindPassWordPresenter>
        implements IFindPassWordView {

    @BindView(R.id.find_password_back)
    TextView back;
    @BindView(R.id.edit_find_password_account)
    EditText account;
    @BindView(R.id.edit_find_password_code)
    EditText code;
    @BindView(R.id.btn_find_password_code)
    Button btnCode;
    @BindView(R.id.edit_new_password)
    EditText password;
    @BindView(R.id.btn_find_password)
    Button find;
    private Typeface iconFont;
    private boolean isVision = true;//是否显示密码
    private boolean isUpdate = false;
    private String account1;//EditText获得的帐号
    private String password1;//EditText获得的密码
    private String type = "REGEDIT";

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_find_password;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        back.setTypeface(iconFont);
        //点击接收验证码
        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initCodeData();
                CountDownButtonHelper helper = new CountDownButtonHelper(btnCode,
                        "后重发", 60, 1);


                helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

                    @Override
                    public void finish() {
                        btnCode.setText("获取验证码");
//                        Toast.makeText(RegisterActivity.this, "倒计时结束",
//                                Toast.LENGTH_SHORT).show();
                    }
                });
                helper.start();
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });


        final Drawable[] drawables = password.getCompoundDrawables();
        final int eyeWidth = drawables[2].getBounds().width();// 眼睛图标的宽度
        final Drawable drawableEyeOpen = getResources().getDrawable(R.mipmap.eyes2);
        drawableEyeOpen.setBounds(drawables[2].getBounds());


        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // getWidth,getHeight必须在这里处理
                    float et_pwdMinX = v.getWidth() - eyeWidth - password.getPaddingRight();
                    float et_pwdMaxX = v.getWidth();
                    float et_pwdMinY = 0;
                    float et_pwdMaxY = v.getHeight();
                    float x = event.getX();
                    float y = event.getY();

                    if (x < et_pwdMaxX && x > et_pwdMinX && y > et_pwdMinY && y < et_pwdMaxY) {
                        isVision = !isVision;

                        if (isVision) {
                            password.setCompoundDrawables(drawables[0], drawables[3], drawables[2], null);

                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        } else {
                            password.setCompoundDrawables(drawables[0], drawables[3], drawableEyeOpen, null);
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        }
                    }
                }
                return false;
            }
        });
//        EventBus.getDefault().register(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getEditTextPhone();//获取账号
        getEditTextPassword();//获取密码
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //设置新密码
    private void initData() {
        OkGo.<String>post(URL.FINDPASSWORD_URL)
                .params("account", account1)
                .params("code", code.getText().toString())
                .params("passwd", password1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1 = new JSONObject(json);
                            Toast.makeText(FindPasswordActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //获取验证码
    private void initCodeData() {
        OkGo.<String>post(URL.CODE_URL)
                .params("account", account1)
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1 = new JSONObject(json);
                            Toast.makeText(FindPasswordActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getEditTextPassword() {
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //重新写入了密码
                password1 = s.toString();
                isUpdate = true;
                find.setBackgroundColor(Color.parseColor("#35BB8A"));
            }
        });
    }

    private void getEditTextPhone() {
        account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                account1 = s + "";
            }
        });
    }

    @Override
    public void finishRefresh() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
