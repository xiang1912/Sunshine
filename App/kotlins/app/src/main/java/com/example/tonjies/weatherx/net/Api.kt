package com.example.tonjies.weatherx.net

import com.example.tonjies.weatherx.module.weather.bean.Weather
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable


interface Api {
    //查询城市信息
    @GET("china/{provinceCode}/{cityCode}")
    fun getCity(@Path("provinceCode") city: String, @Path("cityCode") countryId: String): Observable<ResponseBody>

    //查询天气信息

//    @GET("weather?cityid=&key=bc0418b57b2d4918819d3974ac1285d9")
    @GET("weather?cityid=&key=100dd7683bfb45229c53de9cb377706f  ")
    fun getWeatherInfo(@Query("cityid") weatherId: String):Observable<Weather>

}