package com.dl7.shopping.module.activity.mysetting.changename;

import android.content.Intent;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.shopping.R;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.FirstEvent;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

/**
 * 改变昵称
 * Created by MC.Zeng on 2017-07-06.
 */

public class ChangeNameActivity extends BaseActivity<ChangeNamePresenter>
        implements IChangeNameView {
    @BindView(R.id.tv_change_name_back)
    TextView back;
    @BindView(R.id.tv_change_name_save)
    TextView save;
    @BindView(R.id.et_change_name)
    EditText newName;
    @BindView(R.id.img_change_name_delete)
    ImageView delete;
    private Typeface iconFont;
    private String uid;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        newName.setText(name);
        uid = CommonMethod.getUid(this);

        back.setTypeface(iconFont);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSaveData();
            }
        });

        //清除键
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newName.setText("");
            }
        });

        newName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pas = "^[a-zA-Z0-9\u4e00-\u9fa5]{3,10}";

                Pattern p = Pattern.compile(pas);
                Matcher m = p.matcher(newName.getText().toString());
                if (m.matches()) {

                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });
                } else {
                    if (newName.getText().toString().length() < 3) {//判断长度大于3
                        Toast.makeText(ChangeNameActivity.this, "昵称3-10个字符", Toast.LENGTH_SHORT).show();
                    }

//                    Toast.makeText(ChangeNameActivity.this, "昵称3-10个字符，可由中英文、数字组成,请正确输入", Toast.LENGTH_SHORT).show();
                    //如果输入有误就不给返回
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(ChangeNameActivity.this, "昵称3-10个字符，可由中英文、数字组成,请正确输入", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        EventBus.getDefault().register(this);


//        if (isTrue){
//            back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    finish();
//                }
//            });
//        }else {
//            Toast.makeText(this, "昵称3-10个字符，可由中英文、数字组成，请正确输入", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //保存昵称
    private void initSaveData() {
        OkGo.<String>post(URL.UPDATE_URL)
                .params("id", uid)
                .params("name", newName.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);

                        try {
                            JSONObject j1 = new JSONObject(json);


                            Toast.makeText(ChangeNameActivity.this, j1.getString("message"), Toast.LENGTH_SHORT).show();

                            EventBus.getDefault().post(
                                    new FirstEvent(newName.getText().toString()));

                            //结束页面
                            finish();


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
