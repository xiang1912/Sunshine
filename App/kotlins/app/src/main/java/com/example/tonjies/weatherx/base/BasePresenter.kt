package com.example.tonjies.weatherx.base

open class BasePresenter<T : BaseView> {
    lateinit var mView:T
}