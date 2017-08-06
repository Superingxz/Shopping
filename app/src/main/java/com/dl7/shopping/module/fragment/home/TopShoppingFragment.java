package com.dl7.shopping.module.fragment.home;

/**
 * 线上商城
 * Created by MC.Zeng on 2017-06-27.
 */

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.home.baseview.ITopShoppingView;
import com.dl7.shopping.module.fragment.home.presenter.TopShoppingPresenter;

public class TopShoppingFragment extends BaseFragment<TopShoppingPresenter>
        implements ITopShoppingView {

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_topshopping;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

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
