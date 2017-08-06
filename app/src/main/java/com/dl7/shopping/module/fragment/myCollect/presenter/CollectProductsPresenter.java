package com.dl7.shopping.module.fragment.myCollect.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.myCollect.baseview.ICollectProductsView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CollectProductsPresenter implements IBasePresenter {
    private final ICollectProductsView mView;

    public CollectProductsPresenter(ICollectProductsView mView) {
        this.mView = mView;
    }
}
