package com.dl7.shopping.module.activity.mine.register;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class RegisterPresenter implements IBasePresenter {
    private final IRegisterView mView;

    public RegisterPresenter(IRegisterView mView) {
        this.mView = mView;
    }
}
