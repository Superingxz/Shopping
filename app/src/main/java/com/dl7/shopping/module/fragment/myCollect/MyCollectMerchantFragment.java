package com.dl7.shopping.module.fragment.myCollect;

import com.dl7.shopping.R;
import com.dl7.shopping.module.base.BaseFragment;
import com.dl7.shopping.module.fragment.myCollect.baseview.ICollectMerchantView;
import com.dl7.shopping.module.fragment.myCollect.presenter.CollectMerchantPresenter;


/**
 * Created by MC.Zeng on 2017-07-07.
 */
public class MyCollectMerchantFragment extends BaseFragment<CollectMerchantPresenter>
        implements ICollectMerchantView {

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
