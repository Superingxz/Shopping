package com.dl7.shopping.module.activity.home.waterstore;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class WaterStorePresenter implements IBasePresenter {
    private final IWaterStoreView mView;

    public WaterStorePresenter(IWaterStoreView mView) {
        this.mView = mView;
    }
}
