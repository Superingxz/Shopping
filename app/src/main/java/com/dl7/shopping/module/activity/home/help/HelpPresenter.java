package com.dl7.shopping.module.activity.home.help;

import com.dl7.shopping.module.base.IBasePresenter;

/**
 * Created by Administrator on 2017/7/23.
 */

public class HelpPresenter implements IBasePresenter {
    private final IHelpView mView;

    public HelpPresenter(IHelpView mView) {
        this.mView = mView;
    }
}
