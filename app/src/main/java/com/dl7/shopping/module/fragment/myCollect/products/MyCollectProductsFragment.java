package com.dl7.shopping.module.fragment.myCollect.products;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.myCollect.allcollect.MyCollectAllCollectFragment;
import com.dl7.shopping.module.fragment.myCollect.classify.MyCollectClassifyFragment;
import com.dl7.shopping.utils.FontManager;

import butterknife.BindView;


/**
 * Created by MC.Zeng on 2017-07-07.
 */

public class MyCollectProductsFragment extends BaseFragment<CollectProductsPresenter>
        implements ICollectProductsView {
    @BindView(R.id.tv_collect_allcollect)
    TextView tvAllCollect;
    @BindView(R.id.tv_collect_allcollect_triangle)
    TextView tvAllCollectTriangle;
    @BindView(R.id.ll_collect_allcollect)
    LinearLayout llAllCollect;
    @BindView(R.id.tv_collect_classify)
    TextView tvClassify;
    @BindView(R.id.tv_collect_classify_triangle)
    TextView tvClassifyTriangle;
    @BindView(R.id.ll_collect_classify)
    LinearLayout llClassify;
    private MyCollectAllCollectFragment fragment1;
    private MyCollectClassifyFragment fragment2;
    private Fragment mFragment;//当前显示的Fragment
    private Typeface iconFont;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_collect_product;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        iconFont = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);
        tvAllCollectTriangle.setTypeface(iconFont);
        tvClassifyTriangle.setTypeface(iconFont);

        fragment1 = new MyCollectAllCollectFragment();
        fragment2 = new MyCollectClassifyFragment();

        llAllCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAllCollect.setTextColor(Color.parseColor("#35bb8a"));
                tvAllCollectTriangle.setVisibility(View.VISIBLE);
                tvClassify.setTextColor(Color.parseColor("#1a1a1a"));
                tvClassifyTriangle.setVisibility(View.GONE);
                switchFragment(fragment1);
            }
        });

        llClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAllCollect.setTextColor(Color.parseColor("#1a1a1a"));
                tvAllCollectTriangle.setVisibility(View.GONE);
                tvClassify.setTextColor(Color.parseColor("#35bb8a"));
                tvClassifyTriangle.setVisibility(View.VISIBLE);
                switchFragment(fragment2);
            }
        });

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.collect_fragment, fragment1).commit();
        mFragment = fragment1;
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void goTop() {

    }


    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getFragmentManager().beginTransaction().hide(mFragment)
                        .add(R.id.collect_fragment, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void finishRefresh() {

    }
}
