package com.dl7.shopping.module.activity.home.combo;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.ComboItemAdapter;
import com.dl7.shopping.api.URL;
import com.dl7.shopping.bean.ComboItemBean;
import com.dl7.shopping.module.activity.home.combo.baseview.ComboItemView;
import com.dl7.shopping.module.activity.home.combo.presenter.ComboItemPresenter;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 第二级的套餐页面
 * Created by MC.Zeng on 2017-07-29.
 */

public class ComboItemActivity extends BaseActivity<ComboItemPresenter>
        implements ComboItemView {

    @BindView(R.id.tv_combo_back)
    TextView back;
    @BindView(R.id.rl_combo)
    RelativeLayout rlCombo;
    @BindView(R.id.line_combo)
    TextView lineCombo;
    @BindView(R.id.lv_combo)
    ListView listView;
    private Typeface iconFont;
    private String storeID;
    private String goodsID;
    private List<ComboItemBean.DataBean> mList = new ArrayList<>();
    private ComboItemAdapter adapter;
    private String addressID;


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

        Intent intent = getIntent();
        storeID = intent.getStringExtra("storeID");
        goodsID = intent.getStringExtra("goodsID");
        addressID = intent.getStringExtra("addressID");

        back.setTypeface(iconFont);

        adapter = new ComboItemAdapter(mList, this);
        listView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initData();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    //获取数据
    private void initData() {
        OkGo.<String>post(URL.COMBOITEM_URL)
                .params("goods_id", goodsID)
                .params("store_id", storeID)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String json = response.body().toString();
                        Log.i("onSuccess: ", json);
                        Gson gson = new Gson();
                        ComboItemBean comboItemBean = gson.fromJson(json, ComboItemBean.class);
                        mList.addAll(comboItemBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void finishRefresh() {

    }
}
