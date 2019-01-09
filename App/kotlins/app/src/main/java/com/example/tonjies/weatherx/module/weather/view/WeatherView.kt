package com.example.tonjies.weatherx.module.weather.view

import com.example.tonjies.weatherx.base.BaseView
import com.example.tonjies.weatherx.module.weather.bean.Weather

/**
 * 天气View接口
 */
interface WeatherView : BaseView {
    //返回天气信息结果
    fun setData(weather: Weather)
}