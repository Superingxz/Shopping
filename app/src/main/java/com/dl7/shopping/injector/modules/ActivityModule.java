package com.dl7.shopping.injector.modules;

import android.app.Activity;

import com.dl7.shopping.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by moligy on 2017/7/18.
 * Activity Module
 */
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity getActivity() {
        return mActivity;
    }
}
