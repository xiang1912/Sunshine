package com.example.tonjies.weatherx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.tonjies.weatherx.module.weather.WeatherActivity
import com.example.tonjies.weatherx.util.ShareUtils
import org.jetbrains.anko.startActivity
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weather=ShareUtils.getString(this,"weather","")
        if(!TextUtils.isEmpty(weather)){
            startActivity<WeatherActivity>()
        }
    }
}
