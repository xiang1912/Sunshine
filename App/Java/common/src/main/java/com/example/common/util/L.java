package com.example.common.util;

import android.util.Log;

/**
 * Created by 舍长 on 2018/12/12
 * describe:
 */
public class L {


    //打印d级别的日志
    public static void d(Object msg) {
        String string = msg.toString();
        Log.d("helloWorld", string);
    }
}
