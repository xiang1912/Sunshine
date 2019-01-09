package com.example.tonjies.weatherx.module.weather

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.tonjies.weatherx.R
import com.example.tonjies.weatherx.R.id.*
import com.example.tonjies.weatherx.base.MvpActivity
import com.example.tonjies.weatherx.module.select.SelectActivity
import com.example.tonjies.weatherx.module.weather.bean.Weather
import com.example.tonjies.weatherx.module.weather.presenter.WeatherPresenter
import com.example.tonjies.weatherx.module.weather.view.WeatherView
import com.example.tonjies.weatherx.util.AppManger
import com.example.tonjies.weatherx.util.L
import com.example.tonjies.weatherx.util.ShareUtils
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.item_city.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.w3c.dom.Text

/**
 * 天气详情页面
 */
class WeatherActivity : MvpActivity<WeatherPresenter>(), WeatherView {

    //P层
    private var weatherPresenter = WeatherPresenter()

    private var pressTime: Long = 0

    //WeatheId
    private var weatherId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //将背景延伸到状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_weather)
        weatherId = ShareUtils.getString(this, "weatherId", "")
        if (weatherId != "") {
            weatherPresenter.requestWeather(weatherId!!)
        } else {
            val intent = intent
            weatherId = intent.getStringExtra("weatherId")
        }
        weatherPresenter.mView = this
        imgPlace.setOnClickListener {
            startActivity<SelectActivity>()
        }
        swipeRefresh.setOnRefreshListener {
            toast("当前已经是最新数据")
            weatherPresenter.requestWeather(weatherId!!)
        }
    }

    /**
     * 设置天气数据
     */
    override fun setData(weather: Weather) {
        swipeRefresh.visibility = View.VISIBLE//显示页面布局
        swipeRefresh.isRefreshing = false
        val cityName = getWeather(weather).basic.city//城市名
//        val updateTime: String = getWeather(weather).update.loc.split(" ")[1]//更新时间
        val degree = getWeather(weather).now.tmp + "℃"//温度
        val weatherInfo = getWeather(weather).now.cond_txt//天气

        txtCity.text = cityName//城市
        txtTemp.text = degree//当前温度
        txtInfo.text = weatherInfo//当前天气状况

        val aqi = getWeather(weather).aqi.city.aqi //aql
        val pm25 = getWeather(weather).aqi.city.pm25//pm2.5
        L.d("sss$aqi,$pm25")
        txtAqi.text = aqi//aqi指数
        txtPm25.text = pm25//pm25

        val comfort = "舒适度：${getWeather(weather).suggestion.comf.txt}"
        val carWash = "洗车指数:${getWeather(weather).suggestion.cw.txt}"
        val sport = "运动建议：${getWeather(weather).suggestion.sport.txt}"

        txtComfort.text = comfort
        txtWash.text = carWash
        txtSport.text = sport

        //最近几天的天气状况
        val daily_forecast = getWeather(weather).daily_forecast
        forecastLayout.removeAllViews()
        for (i in 0..daily_forecast.size) {
            val data = daily_forecast[i].date//日期
            val cond = daily_forecast[i].cond.txt_d//天气状况
            val max = daily_forecast[i].tmp.max//最高温度
            val min = daily_forecast[i].tmp.min//最低温度
//            L.d("日期是：$data 天气状况:$cond 最高温度:$max 最低温度:$min")
            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateText: TextView = view.find(R.id.txtDate)//时间
            val infoText: TextView = view.find(R.id.txtInfo)
            val maxText: TextView = view.find(R.id.txtMax)
            val minText: TextView = view.find(R.id.txtMin)

            dateText.text = data
            infoText.text = cond
            maxText.text = max
            minText.text = min
            forecastLayout.addView(view)
        }


    }


    fun getWeather(weather: Weather) =
            weather.HeWeather[0]


    override fun onBackPressed() {
        val time = System.currentTimeMillis()//当前时间
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManger.exitApp(this)
        }
    }
}
