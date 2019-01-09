package com.example.tonjies.weatherx.module.select.bean

//省份实体类
data class Province(var provinceName: String, var provinceCode: Int)

//城市实体类
data class City(var cityName: String, var cityCode: Int)

//县
data class County(var name: String, var id: Int, var weatherId: String)