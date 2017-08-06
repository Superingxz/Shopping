package com.dl7.shopping.module.fragment.shoppingcart;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class ShoppingCartPresenter implements IBasePresenter {
    private final IShoppingCartView mView;

    public ShoppingCartPresenter(IShoppingCartView mView) {
        this.mView = mView;
    }
}
