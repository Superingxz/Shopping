package com.dl7.shopping.module.activity.home.payment;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class PaymentPresenter implements IBasePresenter {
    private final IPaymentView mView;

    public PaymentPresenter(IPaymentView mView) {
        this.mView = mView;
    }
}
