package com.dl7.shopping.module.activity.mysetting.mineset;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class MineSetPresenter implements IBasePresenter {
    private final IMineSetView mView;

    public MineSetPresenter(IMineSetView mView) {
        this.mView = mView;
    }
}
