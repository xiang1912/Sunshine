package com.example.tonjies.templateproject.module.weather.presenter;

import com.example.common.util.L;
import com.example.tonjies.templateproject.module.weather.bean.Weather;
import com.example.tonjies.templateproject.module.weather.model.WeatherModel;
import com.example.tonjies.templateproject.module.weather.presenter.contract.WeatherContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 舍长 on 2019/1/5
 * describe:
 */
public class WeatherPresenter implements WeatherContract.WeatherIPresenter {

    private WeatherContract.WeatherView weatherView;
    private WeatherModel weatherModel;

    public WeatherPresenter(WeatherContract.WeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModel();
    }

    //请求网络数据
    @Override
    public void requestWeather(final String weatherId) {
        L.d("requestWeather");
        weatherModel.requestWeather(weatherId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Weather weather) {
                        L.d("获取到的天气信息:" + weatherId);
                        weatherView.getData(weather);
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d("----"+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
