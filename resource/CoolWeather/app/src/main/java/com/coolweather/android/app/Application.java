package com.coolweather.android.app;

import com.facebook.stetho.Stetho;

import org.litepal.LitePalApplication;

public class Application extends LitePalApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
