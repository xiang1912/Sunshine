package com.example.tonjies.weatherx.module.select.presenter

import com.example.tonjies.weatherx.base.BasePresenter
import com.example.tonjies.weatherx.ext.BaseSubscriber
import com.example.tonjies.weatherx.ext.exexute
import com.example.tonjies.weatherx.module.select.bean.City
import com.example.tonjies.weatherx.module.select.bean.County
import com.example.tonjies.weatherx.module.select.bean.Province
import com.example.tonjies.weatherx.module.select.view.SelectView
import com.example.tonjies.weatherx.net.NetUtil
import com.example.tonjies.weatherx.util.Config
import com.example.tonjies.weatherx.util.L
import okhttp3.ResponseBody
import org.json.JSONArray
import java.util.ArrayList

class SelectPresenter : BasePresenter<SelectView>() {

    //省列表
    private val provinceList = ArrayList<Province>()

    //城市列表
    private val cityList = ArrayList<City>()

    //县列表
    private val countryList = ArrayList<County>()

    //返回数据列表
    private val dataList = ArrayList<String>()

    //请求级别
    private var LEVEL_PROVINCE = 0//省
    private var LEVEL_CITY = 1//市
    private var LEVEL_COUNT = 2//县

    //选中的省份
    private var selectProvince: Province? = null

    //选中的城市
    private var selectCity: City? = null

    //选中的县
    private var selectCountry: County? = null

    /**
     * 返回当前选中的县的天气编号
     */
    fun queryCoutry(position: Int) {
        //获取当前选中的县
        selectCountry = countryList[position]
        mView.setWeatherId(selectCountry!!.weatherId)
    }

    /**
     * 查询当前城市县
     */
    fun queryCountries(position: Int) {
        //获取当前选中城市
        selectCity = cityList[position];
//        mView.setCities(selectCity!!)

        //获取城市编号
        val cityCode = selectCity!!.cityCode
        //获取省份编号
        val provinceCode = selectProvince!!.provinceCode
        L.d("当前选中的城市是${selectCity!!.cityName},城市编号是$cityCode,省份编号是$provinceCode")
        queryFromServer(provinceCode.toString(), cityCode.toString(), "country")
    }

    /**
     * 查询当前省份城市列表
     */
    fun queryCities(position: Int) {
        //获取当前选中省份
        selectProvince = provinceList[position]
//        mView.setCities(selectProvince!!)
//        Config.provinceCode = position
        //获取省份编号
        val provinceCode = selectProvince!!.provinceCode
        L.d("当前选中的省份是${selectProvince!!.provinceName},省份的编号是${selectProvince!!.provinceCode}")
        queryFromServer(provinceCode.toString(), "", "city")
    }

    /**
     * 查询服务器信息封装类，返回相应的数据
     */
    fun queryFromServer(provinceCode: String, cityCode: String, type: String) {
        var netUtil = NetUtil()
        L.d("省份id是$provinceCode,城市id是$cityCode")
        netUtil.getCity(provinceCode, cityCode)
                .exexute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {
                        val s: String = t.string()
                        L.d(s)
                        when (type) {
                            "province" -> {
                                L.d("当前选择的是省份")
                                setProvince(s)
                            }
                            "city" -> {
                                L.d("当前选择的是城市")
                                setCities(s)
                            }
                            "country" -> {
                                L.d("当前选择的是县")
                                setCoutries(s)
                            }
                        }
                    }

                    override fun onError(e: Throwable) {
                        L.d("${e.message}")
                    }
                })
    }

    /**
     * 设置县
     */
    private fun setCoutries(s: String) {
        L.d("获取的县级Json数据$s")
        val allCountries = JSONArray(s)
        countryList.clear()
        for (i in 0 until allCountries.length()) {
            val jsonObject = allCountries.getJSONObject(i)
            val country = County(jsonObject.getString("name"), jsonObject.getInt("id"), jsonObject.getString("weather_id"))
            countryList.add(country)
            dataList.clear()
            for (country in countryList) {
                dataList.add(country.name)
            }
            mView.setData(dataList, LEVEL_COUNT, selectCity!!.cityName)
        }
    }

    /**
     * 设置城市
     */
    private fun setCities(string: String?) {
        L.d("获取到的城市信息$string")
        val allCities = JSONArray(string)//解析获取到的城市Json数据
        cityList.clear()//清除城市列表
        //将获取到的城市添加到城市集合中，然后将每个城市的名字提取出来，赋值给dataList集合
        for (i in 0 until allCities.length()) {
            val jsonObject = allCities.getJSONObject(i)
            val city = City(jsonObject.getString("name"), jsonObject.getInt("id"))
            cityList.add(city)
            dataList.clear()
            for (city in cityList) {
                dataList.add(city.cityName)
            }
            //
            mView.setData(dataList, LEVEL_CITY, selectProvince!!.provinceName)
        }
    }

    /**
     * 设置省份
     */
    private fun setProvince(string: String?) {
        L.d("-------")
        val allProvinces = JSONArray(string)
        provinceList.clear()
        for (i in 0 until allProvinces.length()) {
            val province = allProvinces.getJSONObject(i)
            val provinces = Province(province.getString("name"), province.getInt("id"))
            provinceList.add(provinces)

            dataList.clear()

            for (province in provinceList) {
                dataList.add(province.provinceName)
            }

            mView.setData(dataList, LEVEL_PROVINCE, "中国")
        }
    }
}