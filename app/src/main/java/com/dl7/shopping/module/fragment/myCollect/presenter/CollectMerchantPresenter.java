package com.dl7.shopping.module.fragment.myCollect.presenter;

import com.dl7.shopping.module.base.IBasePresenter;
import com.dl7.shopping.module.fragment.myCollect.baseview.ICollectMerchantView;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CollectMerchantPresenter implements IBasePresenter {
    private final ICollectMerchantView mView;

    public CollectMerchantPresenter(ICollectMerchantView mView) {
        this.mView = mView;
    }
}
