package com.dl7.shopping.module.fragment.home.phonecart.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.home.phonecart.baseview.IPhoneDetailTopListViewView;

/**
 * Created by Administrator on 2017/8/1.
 */

public class PhoneDetailTopListViewPresenter implements IBasePresenter {
    private final IPhoneDetailTopListViewView mView;

    public PhoneDetailTopListViewPresenter(IPhoneDetailTopListViewView mView) {
        this.mView = mView;
    }
}
