package com.dl7.shopping.module.fragment.home.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.home.baseview.IHomeTopView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class HomeTopPresenter implements IBasePresenter {
    private final IHomeTopView mView;

    public HomeTopPresenter(IHomeTopView mView) {
        this.mView = mView;
    }
}
