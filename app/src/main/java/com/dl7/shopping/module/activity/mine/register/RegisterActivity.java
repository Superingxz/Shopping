package com.dl7.shopping.module.activity.mine.register;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.module.activity.mine.login.LoginActivity;
import com.dl7.shopping.module.activity.mine.login.LoginOrRegisterActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.CountDownButtonHelper;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 注册
 * Created by MC.Zeng on 2017-07-05.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter>
        implements IRegisterView {

    @BindView(R.id.tv_register_back)
    TextView back;
    @BindView(R.id.edit_register_account)
    EditText account;
    @BindView(R.id.edit_register_password)
    EditText password;
    @BindView(R.id.edit_register_code)
    EditText code;
    @BindView(R.id.btn_register_code)
    Button btnCode;
    @BindView(R.id.rbn_register_agreement)
    RadioButton agreement;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private Typeface iconFont;
    private String type = "REGEDIT";
    private boolean isAgreement = false;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        back.setTypeface(iconFont);
        agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agreement.setChecked(!isAgreement);
                isAgreement = !isAgreement;
                if (isAgreement == true) {
                    btnRegister.setBackgroundColor(Color.parseColor("#35bb8a"));
                } else {
                    btnRegister.setBackgroundColor(Color.parseColor("#e6e6e6"));
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAgreement == true) {
                    if (account.getText().length() == 11 && password.getText().length() >= 6 && password.getText().length() <= 12 && code.getText().length() == 4) {
                        initData();
                    } else {
                        Toast.makeText(RegisterActivity.this, "请正确填写相关内容", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "请同意相关协议", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //获取验证码
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    private void initData() {
        //注册接口
        OkGo.<String>post("http://192.168.1.77:17317/member/api/1.0/register")
                .params("account", account.getText().toString() + "")
                .params("passwd", password.getText().toString() + "")
                .params("code", code.getText().toString() + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1 = new JSONObject(json);
                            Toast.makeText(RegisterActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();
                            if (j1.getString("event").equals("200")) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent.putExtra("account", account.getText().toString());
                                intent.putExtra("password", password.getText().toString());
                                startActivity(intent);
                                finish();
                                LoginOrRegisterActivity.instance.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    private void initCodeData() {
        //验证码接口
        OkGo.<String>post("http://192.168.1.77:17317/sms/api/1.0/send")
                .params("account", account.getText().toString() + "")
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        try {
                            JSONObject j1 = new JSONObject(json);
                            Toast.makeText(RegisterActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });
    }

    @Override
    public void finishRefresh() {

    }
}
