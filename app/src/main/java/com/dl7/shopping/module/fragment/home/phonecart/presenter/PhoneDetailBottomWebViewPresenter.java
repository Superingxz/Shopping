package com.dl7.shopping.module.fragment.home.phonecart.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.home.phonecart.baseview.IPhoneDetailBottomWebViewView;

/**
 * Created by Administrator on 2017/8/1.
 */

public class PhoneDetailBottomWebViewPresenter implements IBasePresenter {
    private final IPhoneDetailBottomWebViewView mView;

    public PhoneDetailBottomWebViewPresenter(IPhoneDetailBottomWebViewView mView) {
        this.mView = mView;
    }
}
