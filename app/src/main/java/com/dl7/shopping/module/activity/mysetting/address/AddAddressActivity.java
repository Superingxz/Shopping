package com.dl7.shopping.module.activity.mysetting.address;

import android.content.Intent;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.module.activity.mysetting.address.presenter.AddAddressPresenter;
import com.dl7.shopping.module.activity.mysetting.address.baseview.IAddAddressView;
import com.dl7.shopping.module.activity.mysetting.cityselect.CitySelectActivity;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.rxbus.event.SecondEvent;
import com.dl7.shopping.utils.FontManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * 新建地址页面
 * Created by MC.Zeng on 2017-07-07.
 */

public class AddAddressActivity extends BaseActivity<AddAddressPresenter>
        implements IAddAddressView {

    @BindView(R.id.tv_address_add_back)
    TextView back;
    @BindView(R.id.et_address_add_name)
    EditText etAddressAddName;
    @BindView(R.id.rl_address_add_name)
    LinearLayout rlAddressAddName;
    @BindView(R.id.et_address_add_phone)
    EditText etAddressAddPhone;
    @BindView(R.id.rl_address_add_phone)
    LinearLayout rlAddressAddPhone;
    @BindView(R.id.tv_address_add_city)
    TextView city;
    @BindView(R.id.rl_address_add_city)
    RelativeLayout address1;
    @BindView(R.id.tv_address_add_address2)
    TextView tvAddressAddAddress2;
    @BindView(R.id.rl_address_add_address2)
    RelativeLayout address2;
    @BindView(R.id.et_address_add_detail)
    EditText etAddressDetail;
    @BindView(R.id.tv_address_add_icon)
    TextView tvAddressAddIcon;
    @BindView(R.id.rl_address_add_default)
    RelativeLayout rlAddressAddDefault;
    @BindView(R.id.tv_address_add_save)
    TextView save;
    private Typeface iconFont;
    private String msg;
    private String addressDetail;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_address_add;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        EventBus.getDefault().register(this);

        back.setTypeface(iconFont);
        etAddressDetail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addressDetail = s + "";
            }
        });

        address1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAddressActivity.this, CitySelectActivity.class);
                startActivity(intent);
            }
        });
//        address2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(AddAddressActivity.this,AreaSelectActivity.class);
//                startActivity(intent);
//            }
//        });

        //保存的点击
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //得到位置的经纬度
                OkGo.<String>get("http://restapi.amap.com/v3/geocode/geo?address=" + msg + addressDetail + "&output=JSON&key=5caf8abd47a85a4765f1ff00c9d975b4")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                String json = response.body().toString();
                                Log.i("onSuccess: ", json);
                            }
                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }


    @Subscribe
    public void onEventMainThread(SecondEvent event) {

        msg = event.getMsg();
        Log.i("onEventMainThread: ", msg);

        city.setText(msg);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finishRefresh() {

    }
}
