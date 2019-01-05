package com.example.common.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by 舍长 on 2018/12/12
 * describe:
 */
public class BaseApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取Context
        context = getApplicationContext();
        //数据库查看工具，查看地址chrome://inspect/#devices
        Stetho.initializeWithDefaults(this);
    }

    //创建一个静态的方法，以便获取context对象
    public static Context getContext() {
        return context;
    }
}
