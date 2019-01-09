package com.example.tonjies.weatherx.module.weather.bean

data class HeWeather(
    var basic: Basic,
    var update: Update,
    var status: String,
    var now: Now,
    var daily_forecast: List<DailyForecast>,
    var hourly: List<Hourly>,
    var aqi: Aqi,
    var suggestion: Suggestion
)