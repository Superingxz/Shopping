package com.dl7.shopping.module.activity.mysetting.waterstore;

import android.graphics.Typeface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.WaterStoreAdapter;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.CommonMethod;
import com.dl7.shopping.utils.FontManager;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;

/**
 * 选择水店页面
 * Created by MC.Zeng on 2017-07-17.
 */

public class WaterStoreActivity extends BaseActivity<WaterStorePresenter>
        implements IWaterStoreView {

    @BindView(R.id.tv_water_store_back)
    TextView back;
    @BindView(R.id.lv_water_store)
    PullToRefreshListView listView;
    @BindView(R.id.btn_water_store_confirm)
    Button btnWaterStoreConfirm;
    @BindView(R.id.ll_water_store)
    LinearLayout llWaterStore;
    private Typeface iconFont;
    private String uid;
    private String[] name = {"天河区", "天河区", "天河区", "asda", "asdas", "Asda", "Asdasd", "天河区", "天河区", "天河区", "asda", "asdas", "Asda", "Asdasd", "天河区", "天河区", "天河区", "asda", "asdas", "Asda", "Asdasd", "天河区", "天河区", "天河区", "asda", "asdas", "Asda", "Asdasd"};
    private WaterStoreAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_water_store;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
//使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);

        uid = CommonMethod.getUid(this);

        back.setTypeface(iconFont);

        adapter = new WaterStoreAdapter(name, this);
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                adapter.setSelectItem(position);
                adapter.notifyDataSetChanged();
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
