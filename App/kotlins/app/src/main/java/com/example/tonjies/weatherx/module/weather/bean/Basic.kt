package com.example.tonjies.weatherx.module.weather.bean

data class Basic(
    var cid: String,
    var location: String,
    var parent_city: String,
    var admin_area: String,
    var cnty: String,
    var lat: String,
    var lon: String,
    var tz: String,
    var city: String,
    var id: String,
    var update: Update
)