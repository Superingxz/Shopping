package com.dl7.shopping.module.fragment.home.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.home.baseview.IBottomShoppingView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class BottomShoppingPresenter implements IBasePresenter {
    private final IBottomShoppingView mView;

    public BottomShoppingPresenter(IBottomShoppingView mView) {
        this.mView = mView;
    }
}
