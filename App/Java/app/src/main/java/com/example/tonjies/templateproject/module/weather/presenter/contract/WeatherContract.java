package com.example.tonjies.templateproject.module.weather.presenter.contract;

import com.example.tonjies.templateproject.module.weather.bean.Weather;

/**
 * Created by 舍长 on 2019/1/5
 * describe:
 */
public interface WeatherContract {

    interface WeatherView {
        //返回天气实体类
        void getData(Weather weather);
    }

    interface WeatherIPresenter {
        //查询天气信息
        void requestWeather(String weatherId);
    }
}
