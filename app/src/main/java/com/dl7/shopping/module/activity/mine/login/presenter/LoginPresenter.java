package com.dl7.shopping.module.activity.mine.login.presenter;

import com.dl7.shopping.module.activity.mine.login.view.ILoginView;
import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class LoginPresenter implements IBasePresenter {
    private final ILoginView mView;

    public LoginPresenter(ILoginView mView) {
        this.mView = mView;
    }
}
