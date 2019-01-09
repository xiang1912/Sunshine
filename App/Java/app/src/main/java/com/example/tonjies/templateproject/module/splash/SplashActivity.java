package com.example.tonjies.templateproject.module.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.common.util.ACatch;
import com.example.tonjies.templateproject.R;
import com.example.tonjies.templateproject.module.select.SelectActivity;
import com.example.tonjies.templateproject.module.weather.WeatherActivity;
import com.example.tonjies.templateproject.util.AStatic;

/**
 * Created by 舍长
 * describe:用来控制第一次进入的页面是哪一个
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String weatherId = ACatch.getString(AStatic.weatherId, "");
        //如果当前已经存储过天气id，跳转到天气界面
        if (!TextUtils.isEmpty(weatherId)) {
            startActivity(new Intent(this, WeatherActivity.class));
        }
        //如果还没有存储过，跳转到城市选择页面
        else {
            startActivity(new Intent(this, SelectActivity.class));
        }
    }
}
