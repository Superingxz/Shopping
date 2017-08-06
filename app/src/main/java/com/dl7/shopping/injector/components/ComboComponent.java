package com.dl7.shopping.injector.components;


import com.dl7.shopping.injector.PerActivity;
import com.dl7.shopping.injector.modules.ComboModule;
import com.dl7.shopping.module.activity.home.combo.ComboActivity;

import dagger.Component;

/**
 * Created by long on 2016/8/25.
 */
@Deprecated
@PerActivity
@Component(modules = ComboModule.class)
public interface ComboComponent {
    void inject(ComboActivity activity);
}
