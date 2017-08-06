package com.dl7.shopping.module.activity.home.details;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class DetailsPresenter implements IBasePresenter {
    private final IDetailsView mView;

    public DetailsPresenter(IDetailsView mView) {
        this.mView = mView;
    }
}
