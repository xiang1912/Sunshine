package com.example.tonjies.templateproject.module.select.presenter.contract;

import java.util.List;

/**
 * Created by 舍长 on 2019/1/5
 * describe:城市选择页面Contract接口
 */
public interface SelectContract {
    //View接口
    public interface SelectView {
        //设置列表数据
        void setData(List<String> dataList, int type, String title);
        //返回省份-城市-特定县的天气请求id
        void setWeatherId(String weatherId);
    }

    //P层接口
    public interface SelectIPresenter {
        /**
         * 从服务器请求数据,请求参数依次是从服务器返回的省份Code，城市Code，请求类型，当Code不需要时填入null
         */
        void queryFromServer(String provinceCode, String cityCode, int type);

        //查询选中省份下的城市信息
        void queryCities(int position);

        //查询选中城市下的县级信息
        void queryCountry(int position);

        //查询选中县的天气编号
        void queryCountryWeatherId(int position);
    }
}
