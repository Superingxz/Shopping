package com.dl7.shopping.module.fragment;

/**
 * 底部首页
 * Created by MC.Zeng on 2017-06-27.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.MyFragmentStatePagerAdapter;
import com.dl7.shopping.utils.FontManager;


public class HomeTabFragment extends Fragment{

    private ViewPager mViewPager1;
    private TabLayout mTabLayout;
    private String[] tabTitle = {"首页","订水","电话卡","线上商城","线下商城"};
    private Typeface iconFont;
    private TextView location;

    private TextView citytv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homebottom, container, false);
        initViews(rootView);
        initData();

        return rootView;
    }

    private void initViews(View rootView) {
        Bundle bundle=getArguments();


        //轮播图
//        View cycleImages = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_cycleimages,null);
        mViewPager1 = (ViewPager) rootView.findViewById(R.id.mViewPager1);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.mTabLayout);
        citytv = (TextView) rootView.findViewById(R.id.tv_location);
        citytv.setText(bundle.getString("city"));
        iconFont = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);
        location = (TextView) rootView.findViewById(R.id.iv_location);
        location.setTypeface(iconFont);

//        Bundle bundle = getArguments();//从activity传过来的Bundle
//
//        if(bundle!=null){
//            Log.i("initViews: ",bundle.getString("city") );
//            city.setText(bundle.getString("city"));
//        }


    }

    private void initData() {
        for (int i=0; i<tabTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle[i]));
        }
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#35bb8a"));
        mTabLayout.setTabTextColors(Color.BLACK,Color.parseColor("#35bb8a"));

        mViewPager1.setAdapter(new MyFragmentStatePagerAdapter(getChildFragmentManager(),tabTitle));
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

}
