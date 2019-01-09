package com.example.tonjies.templateproject.module.select.presenter;

import com.example.common.util.L;
import com.example.tonjies.templateproject.module.select.bean.City;
import com.example.tonjies.templateproject.module.select.bean.Country;
import com.example.tonjies.templateproject.module.select.bean.Province;
import com.example.tonjies.templateproject.module.select.model.SelectModel;
import com.example.tonjies.templateproject.module.select.presenter.contract.SelectContract;
import com.example.tonjies.templateproject.util.AStatic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by 舍长 on 2019/1/5
 * describe:城市选择页面P层
 */
public class SelectPresenter implements SelectContract.SelectIPresenter {

    //View接口
    private SelectContract.SelectView selectView;

    //Model变量
    private SelectModel selectModel;

    //省份列表
    private List<Province> provinceList = new ArrayList<>();

    //城市列表
    private List<City> cityList = new ArrayList<>();

    //县列表
    private List<Country> countryList = new ArrayList<>();

    //用来返回，显示到界面上泛形为String类型的列表
    private List<String> dataList = new ArrayList<>();

    //当前选中的省份
    private Province selectProvince;

    //当前选中的城市
    private City selectCity;

    //当前选中的县
    private Country selectCountry;

    public SelectPresenter(SelectContract.SelectView selectView) {
        this.selectView = selectView;
        this.selectModel = new SelectModel();
    }


    /**
     * 查询对应省份的城市数据
     */
    @Override
    public void queryCities(int position) {
        if (position == -1) {

        } else {
            //获取当前选中省份
            selectProvince = provinceList.get(position);
        }
        //获取当前选中省份的标号
        int id = selectProvince.getId();
//        L.d("当前选中的省份是：" + selectProvince.getName() + " 省份的编号是:" + selectProvince.getId());
        queryFromServer(String.valueOf(id), "", AStatic.level_city);
    }

    /**
     * 查询当前城市的县级数据
     */
    @Override
    public void queryCountry(int position) {
        //获取当前选中的城市
        selectCity = cityList.get(position);
        //获取当前选中的城市编号
        int cityCode = selectCity.getId();
        //获取当前选中的省份编号
        int provinceCode = selectProvince.getId();
//        L.d("当前选中的城市是：" + selectCity.getName() + " 当前选中的城市编号是:" + cityCode + " 当前选中的省份编号是:" + provinceCode);
        queryFromServer(String.valueOf(provinceCode), String.valueOf(cityCode), AStatic.level_county);
    }

    //查询
    @Override
    public void queryCountryWeatherId(int position) {
        selectCountry=countryList.get(position);
        selectView.setWeatherId(selectCountry.getWeather_id());
    }

    /**
     * 查询服务器信息封装类，返回相应的数据
     */
    @Override
    public void queryFromServer(String provinceCode, String cityCode, final int type) {
        //请求地点数据
        selectModel.getCity(provinceCode, cityCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            switch (type) {
                                //当前请求的是全国省份的数据
                                case AStatic.level_province:
                                    setProvince(string);
                                    break;
                                //当前请求的是全国城市的数据
                                case AStatic.level_city:
//                                    L.d("" + string);
                                    setCities(string);
                                    break;
                                //当前请求的是全国县级别的数据
                                case AStatic.level_county:
//                                    L.d("当前请求的是全国县级别的数据" + string);
                                    setCountry(string);
                                    break;
                            }
//                            L.d("onNext" + string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d("onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 请求的是省份
     */
    private void setProvince(String string) {
        try {
            //获得省份列表
            JSONArray jsonArray = new JSONArray(string);
            provinceList.clear();//清空之前省份列表的数据，防止数据重复加载
            dataList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //设置省份对象
                Province province = new Province(jsonObject.getInt("id"), jsonObject.getString("name"));

                provinceList.add(province);
                //添加到省份列表集合中
                dataList.add(province.getName());
            }
            selectView.setData(dataList, AStatic.level_province, "中国");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置城市信息
     */
    public void setCities(String cities) {
//        L.d("获取到的城市信息:" + cities);
        try {
            JSONArray jsonArray = new JSONArray(cities);
            cityList.clear();
            dataList.clear();
            //遍历获取到的城市列表集合，将城市实体类添加到城市列表，将城市名添加到数据列表中
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //设置城市对象
                City city = new City(jsonObject.getInt("id"), jsonObject.getString("name"));
                cityList.add(city);
                dataList.add(city.getName());
            }
            //String列表数据，级别为城市，省份名称
            selectView.setData(dataList, AStatic.level_city, selectProvince.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置县级
     */
    public void setCountry(String string) {
//        L.d("获取到的县级数据" + string);
        try {
            JSONArray jsonArray = new JSONArray(string);
            dataList.clear();
            countryList.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                //获取Object对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //设置县对象
                Country country = new Country(jsonObject.getInt("id"),
                        jsonObject.getString("name"), jsonObject.getString("weather_id"));
                countryList.add(country);
                dataList.add(country.getName());
            }
//            L.d("------------------------");
            selectView.setData(dataList, AStatic.level_county, selectCity.getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //销毁View对象
    public void detachView() {
        selectView = null;
    }
}
