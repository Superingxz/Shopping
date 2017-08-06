package com.dl7.shopping.module.fragment.homebottom;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class HomeBottomPresenter implements IBasePresenter {
    private final IHomeBottomView mView;

    public HomeBottomPresenter(IHomeBottomView mView) {
        this.mView = mView;
    }
}
