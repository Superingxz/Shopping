package com.dl7.shopping.module.fragment.home;

/**
 * 线下商城
 * Created by MC.Zeng on 2017-06-27.
 */

import android.graphics.Typeface;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dl7.shopping.R;
import com.dl7.shopping.adapter.BottomShoppingAdapter;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.home.baseview.IBottomShoppingView;
import com.dl7.shopping.module.fragment.home.presenter.BottomShoppingPresenter;
import com.dl7.shopping.utils.FontManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import butterknife.BindView;


public class BottomShoppingFragment extends BaseFragment<BottomShoppingPresenter>
        implements IBottomShoppingView {
    @BindView(R.id.lv_bottom_shopping)
    PullToRefreshListView listView;
    private View layoutHead;
    private Typeface iconFont;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_bottomshopping;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        //使用Font Awesome
        iconFont = FontManager.getTypeface(getContext(), FontManager.FONTAWESOME);
        layoutHead = View.inflate(getContext(), R.layout.layout_bottom_shopping_head,null);
        TextView headIcon = (TextView) layoutHead.findViewById(R.id.tv_bottom_shopping_head_icon);
        headIcon.setTypeface(iconFont);
        //先得到里面的listview在添加头布局
        listView.getRefreshableView().addHeaderView(layoutHead);

        listView.setMode(PullToRefreshBase.Mode.BOTH);

        //刷新
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        BottomShoppingAdapter adapter=new BottomShoppingAdapter(getActivity());
        listView.setAdapter(adapter);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    public void goTop() {

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