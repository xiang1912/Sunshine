package com.example.common.util;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.common.app.BaseApp;

/**
 * Created by 舍长 on 2018/12/12
 * describe:辅助工具类
 */
public class AHelper {

    /**
     * 判断String字符串是否为空
     */
    public static Boolean isEmpty(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断两个String字符串是否有一个为空
     */
    public static Boolean isEmpty(String msg1, String msg2) {
        if (TextUtils.isEmpty(msg1) || TextUtils.isEmpty(msg2)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 弹出短吐司
     */
    public static void show(Object object) {
        String s = object.toString();
        Toast.makeText(BaseApp.getContext(), "" + s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出短吐司，并打印日志
     */
    public static void showLog(Object object) {
        String s = object.toString();
        Toast.makeText(BaseApp.getContext(), "" + s, Toast.LENGTH_SHORT).show();
        L.d("" + s);
    }

}
