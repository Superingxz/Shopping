package com.dl7.shopping.injector.modules;


import com.dl7.shopping.injector.PerActivity;
import com.dl7.shopping.module.activity.home.combo.ComboActivity;
import com.dl7.shopping.module.activity.home.combo.presenter.ComboPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by long on 2016/8/25.
 */
@Deprecated
@Module
public class ComboModule {
    private final ComboActivity mView;

    public ComboModule(ComboActivity view) {
        mView = view;
    }

    @PerActivity
    @Provides
    public ComboPresenter providePresenter() {
        return new ComboPresenter(mView);
    }
}
