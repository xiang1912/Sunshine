package com.example.tonjies.weatherx.module.weather.bean

data class Now(
        var cloud: String,
        var cond_code: String,
        var cond_txt: String,
        var fl: String,
        var hum: String,
        var pcpn: String,
        var pres: String,
        var tmp: String,
        var vis: String,
        var wind_deg: String,
        var wind_dir: String,
        var wind_sc: String,
        var wind_spd: String,
        var cond: CondX
)