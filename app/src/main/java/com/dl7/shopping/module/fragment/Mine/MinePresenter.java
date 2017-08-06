package com.dl7.shopping.module.fragment.Mine;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class MinePresenter implements IBasePresenter {
    private final IMineView mView;

    public MinePresenter(IMineView mView) {
        this.mView = mView;
    }
}
