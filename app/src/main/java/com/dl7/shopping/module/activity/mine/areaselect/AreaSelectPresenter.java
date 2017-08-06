package com.dl7.shopping.module.activity.mine.areaselect;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class AreaSelectPresenter implements IBasePresenter {
    private final IAreaSelectView mView;

    public AreaSelectPresenter(IAreaSelectView mView) {
        this.mView = mView;
    }
}
