package com.dl7.shopping.injector.components;

import com.dl7.shopping.injector.PerActivity;
import com.dl7.shopping.injector.modules.GetWaterModule;
import com.dl7.shopping.module.activity.home.getwater.GetWaterActivity;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/6.
 */

@Deprecated
@PerActivity
@Component(modules = GetWaterModule.class)
public interface GetWaterComponent {
    void inject(GetWaterActivity activity);
}
