package com.dl7.shopping.module.fragment.myCollect.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.myCollect.baseview.IAllCollectView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class AllCollectPresenter implements IBasePresenter {
    private final IAllCollectView mView;

    public AllCollectPresenter(IAllCollectView mView) {
        this.mView = mView;
    }
}
