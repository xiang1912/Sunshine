package com.example.tonjies.weatherx.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tonjies.weatherx.util.AppManger

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManger.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManger.finishActivity()
    }

}
