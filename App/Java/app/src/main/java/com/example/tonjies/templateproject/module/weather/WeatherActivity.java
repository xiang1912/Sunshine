package com.example.tonjies.templateproject.module.weather;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.util.ACatch;
import com.example.common.util.L;
import com.example.tonjies.templateproject.R;
import com.example.tonjies.templateproject.module.select.SelectActivity;
import com.example.tonjies.templateproject.module.weather.bean.Weather;
import com.example.tonjies.templateproject.module.weather.presenter.WeatherPresenter;
import com.example.tonjies.templateproject.module.weather.presenter.contract.WeatherContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 舍长
 * describe: 天气显示界面
 */
public class WeatherActivity extends AppCompatActivity implements WeatherContract.WeatherView {

    //温度
    @BindView(R.id.txtTemp)
    TextView txtTemp;
    //城市
    @BindView(R.id.txtCity)
    TextView txtCity;
    //天气信息
    @BindView(R.id.txtInfo)
    TextView txtInfo;
    //
    @BindView(R.id.forecastLayout)
    LinearLayout forecastLayout;
    @BindView(R.id.forecast)
    LinearLayout forecast;

    //AQI指数
    @BindView(R.id.txtAqi)
    TextView txtAqi;

    //Pm2.5指数
    @BindView(R.id.txtPm25)
    TextView txtPm25;

    //舒适度
    @BindView(R.id.txtComfort)
    TextView txtComfort;

    //洗车指数
    @BindView(R.id.txtWash)
    TextView txtWash;

    //运动指数
    @BindView(R.id.txtSport)
    TextView txtSport;


    //城市选择页面
    @BindView(R.id.imgPlace)
    ImageView imgPlace;

    //下拉刷新
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    //P层接口
    private WeatherPresenter weatherIPresenter;

    //天气id
    private String weatherId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        weatherIPresenter = new WeatherPresenter(this);
        //查看是否已经存储了天气的数据
        weatherId = ACatch.getString("weatherId", "");
        //不等于空，说明之前已经请求过了，直接拿着之前请求过的天气id去气球天气数据即可
        if (!TextUtils.isEmpty(weatherId)) {
            weatherIPresenter.requestWeather(weatherId);
            L.d("从存储库中取出的:" + weatherId);
        }
        weatherIPresenter.requestWeather(weatherId);
        refreshLayout.setVisibility(View.GONE);
        //头部刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        weatherIPresenter.requestWeather(weatherId);
                        refreshLayout.finishRefresh();
                        Toast.makeText(WeatherActivity.this, "更新完毕", Toast.LENGTH_SHORT).show();
                    }
                }, 400);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getData(Weather weather) {
        refreshLayout.setVisibility(View.VISIBLE);
        Weather.HeWeatherBean heWeatherBean = weather.getHeWeather().get(0);
        //城市名
        txtCity.setText(heWeatherBean.getBasic().getCity());
        //温度
        txtTemp.setText(heWeatherBean.getNow().getTmp() + "℃");
        //天气信息
        txtInfo.setText(heWeatherBean.getNow().getCond_txt());
        //aql指数
        txtAqi.setText(heWeatherBean.getAqi().getCity().getAqi());
        //pm2.5
        txtPm25.setText(heWeatherBean.getAqi().getCity().getPm25());
        //舒适度
        txtComfort.setText("舒适度：" + heWeatherBean.getSuggestion().getComf().getTxt());
        //洗车指数
        txtWash.setText("洗车指数:" + heWeatherBean.getSuggestion().getCw().getTxt());
        //运动建议
        txtSport.setText("运动建议:" + heWeatherBean.getSuggestion().getSport().getTxt());
        //最近几天的天气状况
        List<Weather.HeWeatherBean.DailyForecastBean> daily_forecast = heWeatherBean.getDaily_forecast();
        forecastLayout.removeAllViews();
        for (int i = 0; i < daily_forecast.size(); i++) {
            Weather.HeWeatherBean.DailyForecastBean dailyForecastBean = daily_forecast.get(i);
            String data = dailyForecastBean.getDate();//日期
            String cond = dailyForecastBean.getCond().getTxt_d();//天气状况
            String max = dailyForecastBean.getTmp().getMax();//最高温度
            String min = dailyForecastBean.getTmp().getMin();//最低温度
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView tvDate = view.findViewById(R.id.txtDate);
            TextView tvInfo = view.findViewById(R.id.txtInfo);
            TextView maxText = view.findViewById(R.id.txtMax);
            TextView minText = view.findViewById(R.id.txtMin);

            tvDate.setText(data);
            tvInfo.setText(cond);
            maxText.setText(max);
            minText.setText(min);
            forecastLayout.addView(view);
        }
    }

    //跳转到城市选择页面
    @OnClick(R.id.imgPlace)
    public void onViewClicked() {
        startActivity(new Intent(this, SelectActivity.class));
    }
}
