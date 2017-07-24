package com.dl7.shopping.injector.modules;

import android.content.Context;

import com.dl7.shopping.AndroidApplication;
import com.dl7.shopping.local.table.DaoSession;
import com.dl7.shopping.rxbus.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by moligy on 2017/7/18.
 * Application Module
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;
    private final DaoSession mDaoSession;
    private final RxBus mRxBus;

    public ApplicationModule(AndroidApplication application, DaoSession daoSession, RxBus rxBus) {
        mApplication = application;
        mDaoSession = daoSession;
        mRxBus = rxBus;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication.getApplication();
    }

    @Provides
    @Singleton
    RxBus provideRxBus() {
        return mRxBus;
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession() {
        return mDaoSession;
    }
}
