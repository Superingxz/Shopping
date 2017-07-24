package com.dl7.shopping.injector.components;

import android.app.Activity;

import com.dl7.shopping.injector.PerActivity;
import com.dl7.shopping.injector.modules.ActivityModule;

import dagger.Component;

/**
 * Created by moligy on 2017/7/18.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
