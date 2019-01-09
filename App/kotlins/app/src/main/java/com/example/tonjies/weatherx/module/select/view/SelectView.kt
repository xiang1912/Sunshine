package com.example.tonjies.weatherx.module.select.view

import com.example.tonjies.weatherx.base.BaseView
import com.example.tonjies.weatherx.module.select.bean.City
import com.example.tonjies.weatherx.module.select.bean.Province

interface SelectView : BaseView {
    //返回查询到的数据 和当前选中类型以及当前标题
    fun setData(dataList: ArrayList<String>, type: Int, title: String)

    //返回查询到的县的天气编号
    fun setWeatherId(weatherId: String)
}