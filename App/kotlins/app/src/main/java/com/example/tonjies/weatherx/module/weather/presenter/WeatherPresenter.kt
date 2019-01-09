package com.example.tonjies.weatherx.module.weather.presenter

import com.example.tonjies.weatherx.base.BasePresenter
import com.example.tonjies.weatherx.ext.BaseSubscriber
import com.example.tonjies.weatherx.ext.exexute
import com.example.tonjies.weatherx.module.weather.bean.Weather
import com.example.tonjies.weatherx.module.weather.view.WeatherView
import com.example.tonjies.weatherx.net.NetUtil
import com.example.tonjies.weatherx.util.L

class WeatherPresenter : BasePresenter<WeatherView>() {

    /**
     * 查询天气信息
     */
    fun requestWeather(weatherId: String) {
        var netUtil = NetUtil()
        netUtil.weatherInfo(weatherId)
                .exexute(object : BaseSubscriber<Weather>() {
                    override fun onNext(t: Weather) {
                        val s: String = t.toString()
                        L.d("${s.toString()}")
                        mView.setData(t)
                    }

                    override fun onError(e: Throwable) {

                        super.onError(e)
                    }
                })
//                .exexute(object : BaseSubscriber<ResponseBody>() {
//                    override fun onNext(t: ResponseBody) {
//                        val s: String = t.string()
////                        L.d("$s")
//                        mView.setData(s)
//                    }
//
//                    override fun onError(e: Throwable) {
//                        super.onError(e)
//                        L.d("当前错误$e")
//                    }
//                })
    }
}