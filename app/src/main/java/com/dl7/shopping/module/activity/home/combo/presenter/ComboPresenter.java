package com.dl7.shopping.module.activity.home.combo.presenter;

import com.dl7.shopping.module.activity.home.combo.baseview.IComboView;
import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Adminstrator on 2017/7/23.
 */

public class ComboPresenter implements IBasePresenter {
    private final IComboView mView;

    public ComboPresenter(IComboView view) {
        this.mView = view;
    }
}
