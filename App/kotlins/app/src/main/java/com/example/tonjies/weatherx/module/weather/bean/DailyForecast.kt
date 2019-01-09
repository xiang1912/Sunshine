package com.example.tonjies.weatherx.module.weather.bean

data class DailyForecast(
    var date: String,
    var cond: Cond,
    var tmp: Tmp
)