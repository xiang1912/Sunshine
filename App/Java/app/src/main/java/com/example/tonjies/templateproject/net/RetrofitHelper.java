package com.example.tonjies.templateproject.net;



import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 舍长 on 2018/6/4.
 * 舍长: Retrofit请求封装类
 */

public class RetrofitHelper {

    OkHttpClient okHttpClient = new OkHttpClient();
    GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper apiHelper = null;
    private static Retrofit retrofit = null;

    public static RetrofitHelper getInstance() {
        if (apiHelper == null) {
            apiHelper = new RetrofitHelper();
        }
        return apiHelper;
    }

    private RetrofitHelper() {
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Api getServer() {
        return retrofit.create(Api.class);
    }


}
