package com.example.tonjies.weatherx.ext

import rx.Observable
import rx.schedulers.Schedulers


fun <T> Observable<T>.exexute(subscriber: BaseSubscriber<T>) {
    this.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)
}