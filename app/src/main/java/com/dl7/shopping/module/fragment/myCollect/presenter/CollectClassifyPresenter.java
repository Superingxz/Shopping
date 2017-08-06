package com.dl7.shopping.module.fragment.myCollect.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.myCollect.baseview.ICollectClassifyView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CollectClassifyPresenter implements IBasePresenter {
    private final ICollectClassifyView mView;

    public CollectClassifyPresenter(ICollectClassifyView mView) {
        this.mView = mView;
    }
}
