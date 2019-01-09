package com.example.tonjies.templateproject.module.select.model;

import com.example.tonjies.templateproject.app.App;
import com.example.tonjies.templateproject.net.Api;
import com.example.tonjies.templateproject.net.RetrofitHelper;

import java.util.Observer;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by 舍长 on 2019/1/5
 * describe:城市选择页面Model层
 */
public class SelectModel {

    private Api api;

    public SelectModel() {
        api = RetrofitHelper.getInstance().getServer();
    }

    //查询城市信息
    public Observable<ResponseBody> getCity(String provinceCode, String cityCode) {
        return api.getCity(provinceCode, cityCode);
    }
}
