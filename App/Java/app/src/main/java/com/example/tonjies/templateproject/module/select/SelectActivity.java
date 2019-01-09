package com.example.tonjies.templateproject.module.select;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.util.ACatch;
import com.example.common.util.L;
import com.example.tonjies.templateproject.R;
import com.example.tonjies.templateproject.adapter.SelectAdapter;
import com.example.tonjies.templateproject.module.select.presenter.SelectPresenter;
import com.example.tonjies.templateproject.module.select.presenter.contract.SelectContract;
import com.example.tonjies.templateproject.module.weather.WeatherActivity;
import com.example.tonjies.templateproject.util.AStatic;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 舍长
 * describe:城市选择Activity
 */
public class SelectActivity extends AppCompatActivity implements SelectContract.SelectView {

    //返回按钮
    @BindView(R.id.mImgBack)
    ImageView mImgBack;

    //地点标题
    @BindView(R.id.mTxtTitle)
    TextView mTxtTitle;

    //地点列表
    @BindView(R.id.mRecList)
    RecyclerView mRecList;

    //下拉刷新库，这里为了实现回弹效果
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    //布局管理器
    private LinearLayoutManager linearLayoutManager;

    //适配器
    private SelectAdapter selectAdapter;

    //列表数据
    private List<String> mData;

    //P层接口
    private SelectPresenter selectPresenter;

    //当前选中的级别
    private int currentLevel;

    //分隔线
    private DividerItemDecoration decoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将背景延伸到状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        init();//初始化
    }

    /**
     * 初始化数据，控件
     */
    private void init() {

        mImgBack.setVisibility(View.GONE);
        mData = new ArrayList<>();
//        for (int i = 'A'; i < 'Z'; i++) {
//            mData.add("" + (char) i);
//        }
        //初始化recyclerView
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecList.setLayoutManager(linearLayoutManager);
        selectAdapter = new SelectAdapter(this, mData);
        mRecList.setAdapter(selectAdapter);
        selectPresenter = new SelectPresenter(this);
        selectPresenter.queryFromServer("", "", AStatic.level_province);

        decoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        mRecList.addItemDecoration(decoration);
    }

    /**
     * view 就是我们点击的itemView
     */
    public void myItemClick(View view) {
        // 获取点击到d的itemView的位置
        int position = mRecList.getChildAdapterPosition(view);
        //根据不同的类型再次进行请求
        switch (currentLevel) {
            //如果是从省份列表点击的，继续查询省份下的城市数据
            case AStatic.level_province:
                //显示返回按钮
                mImgBack.setVisibility(View.VISIBLE);
                selectPresenter.queryCities(position);
                break;
            //如果是从城市列表点击的，继续查询城市下的县级数据
            case AStatic.level_city:
                mImgBack.setVisibility(View.VISIBLE);
                selectPresenter.queryCountry(position);
                break;
            //如果是从县级点击的,查询天气信息，进入主界面，结束当前Activity
            case AStatic.level_county:
                mImgBack.setVisibility(View.VISIBLE);
                selectPresenter.queryCountryWeatherId(position);
                break;
        }
        Toast.makeText(this, "点击了 " + mData.get(position),
                Toast.LENGTH_SHORT).show();
    }


    /**
     * 接受服务器返回的数
     */
    @Override
    public void setData(List<String> dataList, int type, String title) {
        linearLayoutManager.scrollToPositionWithOffset(0,0);
        mTxtTitle.setText(title);
        mData.clear();
        mData.addAll(dataList);
        selectAdapter.notifyDataSetChanged();
        currentLevel = type;
    }

    //设置天气id
    @Override
    public void setWeatherId(String weatherId) {
        L.d("setWeatherId:"+weatherId);
        ACatch.putString(AStatic.weatherId, weatherId);
        startActivity(new Intent(this,WeatherActivity.class));
    }

    //返回按钮
    @OnClick(R.id.mImgBack)
    public void onViewClicked() {
        switch (currentLevel) {
            case AStatic.level_county:
                selectPresenter.queryCities(-1);
                break;
            case AStatic.level_city:
                mImgBack.setVisibility(View.GONE);
                //查询省份信息
                selectPresenter.queryFromServer("", "", AStatic.level_province);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectPresenter.detachView();
    }
}


