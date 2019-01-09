package com.example.tonjies.templateproject.module.weather.model;

import com.example.tonjies.templateproject.module.weather.bean.Weather;
import com.example.tonjies.templateproject.net.Api;
import com.example.tonjies.templateproject.net.RetrofitHelper;

import io.reactivex.Observable;

/**
 * Created by 舍长 on 2019/1/5
 * describe:
 */
public class WeatherModel {

    private Api api;

    public WeatherModel() {
        api = RetrofitHelper.getInstance().getServer();
    }

    public Observable<Weather> requestWeather(String weatherId) {
        return api.getWeather(weatherId);
    }
}
