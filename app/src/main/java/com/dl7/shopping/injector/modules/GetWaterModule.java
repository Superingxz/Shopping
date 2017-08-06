package com.dl7.shopping.injector.modules;

import com.dl7.shopping.injector.PerActivity;
import com.dl7.shopping.module.activity.home.getwater.GetWaterActivity;
import com.dl7.shopping.module.activity.home.getwater.GetWaterPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/8/6.
 */

@Module
public class GetWaterModule {
    private final GetWaterActivity mView;

    public GetWaterModule(GetWaterActivity view) {
        mView = view;
    }

    @PerActivity
    @Provides
    public GetWaterPresenter providePresenter() {
        return new GetWaterPresenter(mView);
    }
}
