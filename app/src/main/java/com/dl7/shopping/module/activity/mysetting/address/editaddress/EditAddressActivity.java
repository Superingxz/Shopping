package com.dl7.shopping.module.activity.mysetting.address.editaddress;

import android.graphics.Typeface;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 编辑地址页面
 * Created by MC.Zeng on 2017-07-18.
 */

public class EditAddressActivity extends BaseActivity<EditAddressPresenter>
        implements IEditAddressView {
    @BindView(R.id.tv_address_add_back)
    TextView back;
    @BindView(R.id.tv_address_add_city)
    TextView city;
    @BindView(R.id.rl_address_add_city)
    RelativeLayout address1;
    @BindView(R.id.rl_address_add_address2)
    RelativeLayout address2;
    @BindView(R.id.et_address_add_detail)
    EditText etAddressDetail;
    @BindView(R.id.tv_address_add_save)
    TextView save;
    private Typeface iconFont;

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
        back.setTypeface(iconFont);

        EventBus.getDefault().register(this);

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

    @Override
    public void finishRefresh() {

    }
}
