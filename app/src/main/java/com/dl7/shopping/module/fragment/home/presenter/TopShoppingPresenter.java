package com.dl7.shopping.module.fragment.home.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.home.baseview.ITopShoppingView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class TopShoppingPresenter implements IBasePresenter {
    private final ITopShoppingView mView;

    public TopShoppingPresenter(ITopShoppingView mView) {
        this.mView = mView;
    }
}
