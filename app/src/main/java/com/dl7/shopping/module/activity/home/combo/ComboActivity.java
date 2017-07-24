package com.dl7.shopping.module.activity.home.combo;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.ComboAdapter;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;

import butterknife.BindView;


/**
 * 套餐页面
 * Created by MC.Zeng on 2017-07-08.
 */

public class ComboActivity extends BaseActivity<ComboPresenter> implements IComboView {
    @BindView(R.id.tv_combo_back)
    TextView back;
    @BindView(R.id.lv_combo)
    ListView listView;
    private Typeface iconFont;
    private int[] img = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_combo;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        back.setTypeface(iconFont);
        ComboAdapter adapter = new ComboAdapter(img, this);
        listView.setAdapter(adapter);

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
