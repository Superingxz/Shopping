package com.dl7.shopping.module.fragment.home.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.home.baseview.IWaterView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class WaterPresenter implements IBasePresenter {
    private final IWaterView mView;

    public WaterPresenter(IWaterView mView) {
        this.mView = mView;
    }
}
