package com.dl7.shopping.module.fragment.merchant;

/**
 * 商家
 * Created by 曾铭崇 on 2017-06-27.
 */

import android.view.View;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseFragment;


public class MerchantFragment extends BaseFragment<MerchantPresenter>
        implements View.OnClickListener ,IMerchantView{

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_merchant;
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
    public void onClick(View v) {

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