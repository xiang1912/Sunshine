package com.example.tonjies.weatherx.module.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tonjies.weatherx.R
import com.example.tonjies.weatherx.base.BaseActivity
import com.example.tonjies.weatherx.module.select.SelectActivity
import com.example.tonjies.weatherx.module.weather.WeatherActivity
import com.example.tonjies.weatherx.util.ShareUtils
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shlash)
        val weatherId= ShareUtils.getString(this,"weatherId","")
        if(weatherId!=""){
            startActivity<WeatherActivity>()
        }else{
            startActivity<SelectActivity>()
        }
    }
}
