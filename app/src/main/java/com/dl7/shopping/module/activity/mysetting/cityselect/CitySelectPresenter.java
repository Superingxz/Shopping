package com.dl7.shopping.module.activity.mysetting.cityselect;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CitySelectPresenter implements IBasePresenter {
    private final ICitySelectView mView;

    public CitySelectPresenter(ICitySelectView mView) {
        this.mView = mView;
    }
}
