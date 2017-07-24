package com.dl7.shopping.module.activity.mine.mycollect;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.MyCollectFragmentAdapter;
import com.dl7.shopping.module.base.BaseActivity;
import com.dl7.shopping.utils.FontManager;

import butterknife.BindView;


/**
 * 收藏
 * Created by MC.Zeng on 2017-07-07.
 */

public class MyCollectActivity extends BaseActivity<MyCollectPresenter>
        implements IMyCollectView {

    @BindView(R.id.mycollect_back)
    TextView back;
    @BindView(R.id.mycollect_tab)
    TabLayout mTabLayout;
    @BindView(R.id.tv_mycollect_edit)
    TextView edit;
    @BindView(R.id.tv_mycollect_search)
    TextView search;
    @BindView(R.id.mycollect_vp)
    ViewPager mViewPager1;
    private Typeface iconFont;
    private String[] tabTitle = {"商品", "商家"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_mycollect;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        back.setTypeface(iconFont);
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

    private void initData() {
        for (int i = 0; i < tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#35bb8a"));
        mTabLayout.setTabTextColors(Color.BLACK, Color.parseColor("#35bb8a"));

        mViewPager1.setAdapter(new MyCollectFragmentAdapter(getSupportFragmentManager(), tabTitle));
        mViewPager1.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager1.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void finishRefresh() {

    }
}
