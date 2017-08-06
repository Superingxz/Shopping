package com.dl7.shopping.module.activity.mysetting.address.presenter;

import com.dl7.shopping.module.activity.mysetting.address.baseview.IAddAddressView;
import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class AddAddressPresenter implements IBasePresenter {
    private final IAddAddressView mView;

    public AddAddressPresenter(IAddAddressView mView) {
        this.mView = mView;
    }
}
