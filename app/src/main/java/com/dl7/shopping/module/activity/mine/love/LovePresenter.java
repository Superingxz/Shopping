package com.dl7.shopping.module.activity.mine.love;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class LovePresenter implements IBasePresenter {
    private final ILoveView mView;

    public LovePresenter(ILoveView mView) {
        this.mView = mView;
    }
}
