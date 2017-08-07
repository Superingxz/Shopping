package com.dl7.shopping.module.activity.pay;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.utils.FontManager;


/**
 * 支付成功页面
 * Created by MC.Zeng on 2017-07-26.
 */

public class SuccessActivity extends AppCompatActivity{

    private Typeface iconFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_success);
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        TextView back= (TextView) findViewById(R.id.tv_success_back);
        back.setTypeface(iconFont);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
