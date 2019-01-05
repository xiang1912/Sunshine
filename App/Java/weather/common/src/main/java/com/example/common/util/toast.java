package com.example.common.util;

import android.util.Log;

/**
 * Created by 舍长 on 2018/12/30
 * describe:吐司封装工具类
 */
public class toast {

    private final Boolean isOpen = true;

    private final String tag = "helloWorld";

    public toast(String msg) {
        if (isOpen) {
            Log.d(tag, msg);
        }
    }
}
