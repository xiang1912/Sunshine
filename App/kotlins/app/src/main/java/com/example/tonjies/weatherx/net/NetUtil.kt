package com.example.tonjies.weatherx.net

import com.example.tonjies.weatherx.module.weather.bean.Weather
import com.example.tonjies.weatherx.util.L
import okhttp3.ResponseBody
import rx.Observable


class NetUtil {
    /**
     * 获取城市信息
     */
    fun getCity(provinceCode: String, cityCode: String): Observable<ResponseBody> {
        L.d("ss$provinceCode,$cityCode")
        return RetrofitFactory.create(Api::class.java,"").getCity(provinceCode, cityCode)
    }

    /**
     * 获取天气信息
     */
    fun weatherInfo(cityId: String) :Observable<Weather>{
        return RetrofitFactory.create(Api::class.java,"").getWeatherInfo(cityId)
    }
}