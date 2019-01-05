package com.example.common.util;

import android.widget.EditText;

/**
 * Created by 舍长 on 2018/12/14
 * describe:
 */
public class E {
    private static String g(EditText editText) {
        final String trim = editText.getText().toString().trim();
        return trim;
    }
}
