package com.dl7.shopping.module.fragment.merchant;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class MerchantPresenter implements IBasePresenter {
    private final IMerchantView mView;

    public MerchantPresenter(IMerchantView mView) {
        this.mView = mView;
    }
}
