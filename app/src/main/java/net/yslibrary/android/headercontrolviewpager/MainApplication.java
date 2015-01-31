package net.yslibrary.android.headercontrolviewpager;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by yshrsmz on 15/01/31.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
