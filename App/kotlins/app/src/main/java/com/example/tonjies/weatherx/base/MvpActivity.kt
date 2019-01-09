package com.example.tonjies.weatherx.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast

/**
 * Mvp架构
 */
open class MvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun show(msg: String) {
        toast(msg)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
