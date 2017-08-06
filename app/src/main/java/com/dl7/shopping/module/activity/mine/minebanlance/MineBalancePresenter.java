package com.dl7.shopping.module.activity.mine.minebanlance;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class MineBalancePresenter implements IBasePresenter {
    private final IMineBalanceView mView;

    public MineBalancePresenter(IMineBalanceView mView) {
        this.mView = mView;
    }
}
