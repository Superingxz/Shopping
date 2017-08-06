package com.dl7.shopping.module.activity.mysetting.address.presenter;

import com.dl7.shopping.module.activity.mysetting.address.baseview.IEditAddressView;
import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class EditAddressPresenter implements IBasePresenter {
    private final IEditAddressView mView;

    public EditAddressPresenter(IEditAddressView mView) {
        this.mView = mView;
    }
}
