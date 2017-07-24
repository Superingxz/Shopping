package com.dl7.shopping.module.activity.mine.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.module.activity.mine.register.RegisterActivity;
import com.dl7.shopping.utils.FontManager;


/**
 * 登录或者注册
 * Created by MC.Zeng on 2017-07-05.
 */

public class LoginOrRegisterActivity extends AppCompatActivity {


    private Typeface iconFont;
    private TextView back;
    private TextView login;
    private TextView register;
    public static Activity instance;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        Intent intent=getIntent();
        type = intent.getStringExtra("type");
        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        instance=this;
        back = (TextView) findViewById(R.id.login_register_back);
        back.setTypeface(iconFont);
        login = (TextView) findViewById(R.id.tv_login);
        register = (TextView) findViewById(R.id.tv_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginOrRegisterActivity.this, com.dl7.shopping.module.activity.mine.login.LoginActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginOrRegisterActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
