package com.example.tonjies.templateproject.net;

import com.example.tonjies.templateproject.module.refrence.bean.Student;
import com.example.tonjies.templateproject.module.weather.bean.Weather;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by 舍长 on 2018/12/12
 * describe:
 */
public interface Api {

    //基础url
    String baseUrl = "http://guolin.tech/api/";

    //查询城市信息
    @GET("china/{provinceCode}/{cityCode}")
    Observable<ResponseBody> getCity(@Path("provinceCode") String provinceCode, @Path("cityCode") String cityCode);

    //请求网络数据
    @GET("weather?cityid=&key=100dd7683bfb45229c53de9cb377706f")
    Observable<Weather> getWeather(@Query("cityid") String weatherId);
}
