package com.example.tonjies.weatherx.module.select

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.tonjies.weatherx.R
import com.example.tonjies.weatherx.adapter.SelectAdapter
import com.example.tonjies.weatherx.base.MvpActivity
import com.example.tonjies.weatherx.module.select.bean.City
import com.example.tonjies.weatherx.module.select.bean.County
import com.example.tonjies.weatherx.module.select.bean.Province
import com.example.tonjies.weatherx.module.select.presenter.SelectPresenter
import com.example.tonjies.weatherx.module.select.view.SelectView
import com.example.tonjies.weatherx.module.weather.WeatherActivity
import com.example.tonjies.weatherx.util.Config
import com.example.tonjies.weatherx.util.L
import com.example.tonjies.weatherx.util.ShareUtils
import kotlinx.android.synthetic.main.activity_select.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 城市选择界面
 */
class SelectActivity : MvpActivity<SelectPresenter>(), SelectView {
    override fun setWeatherId(weatherId: String) {
        ShareUtils.putString(this,"weatherId",weatherId)
        startActivity<WeatherActivity>("weatherId" to weatherId)
    }

    private var layoutManger: LinearLayoutManager? = null

    private var selectAdapter = SelectAdapter()

    //请求级别
    private var LEVEL_PROVINCE = 0//省
    private var LEVEL_CITY = 1//市
    private var LEVEL_COUNT = 2//县


    //RecyclerView数据集合
    private var mDatas = ArrayList<String>()

    //当前选中的级别
    private var currentLevel: Int? = null

    //进度条
    private var progressDialog: ProgressDialog? = null

    //P层
    private var selectPresenter = SelectPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //将背景延伸到状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_select)

        //初始化本地数据
//        initData()
        //设置view
        initView()
    }

    private fun initView() {
        //设置布局管理器
        layoutManger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        //设置适配器
        selectAdapter = SelectAdapter(this, mDatas)
        mRecList.layoutManager = layoutManger

        mRecList.adapter = selectAdapter
        //查询省份信息
        queryProvinces()
        mImgBack.setOnClickListener {
            if (currentLevel == LEVEL_COUNT) {
                //当选中的级别是县级
                //根据当前选中的城市id进行城市查询
                selectPresenter.queryCities(Config.provinceCode!!)
            } else if (currentLevel == LEVEL_CITY) {
                mImgBack.visibility = View.GONE
                //查询省份信息
                selectPresenter.queryFromServer("", "", "province")
            }
        }
    }

    /**
     * 查询省份信息
     */
    private fun queryProvinces() {
        mTxtTitle.text = "中国"
        mImgBack.visibility = View.GONE//隐藏返回按钮
        selectPresenter.mView = this
        selectPresenter.queryFromServer("", "", "province")
        mImgBack.visibility = View.GONE
    }

    /**
     * 初始化本地数据
     */
    private fun initData() {
        var i: Int = 'A'.toInt()
        while (i < 'Z'.toInt()) {
            mDatas.add("" + i.toChar())
            i++
        }
    }

    /**
     * 设置地点名称
     */
    override fun setData(dataList: ArrayList<String>, type: Int, title: String) {
        layoutManger!!.scrollToPositionWithOffset(0, 0)//指定RecycleView华东到指定position
        mTxtTitle.text = title//设置列表标题
        mDatas.clear()
        for (s in dataList) {
            mDatas.add(s)
        }
        currentLevel = type
        selectAdapter.notifyDataSetChanged()

    }

    override fun show(msg: String) {
        toast(msg)
    }

    /**
     * 实现RecyclerView的点击事件
     */
    fun myItemClick(view: View) {
        //获得itemView的位置
        val position = mRecList.getChildAdapterPosition(view)
        when (currentLevel) {
            //如果当前选择的级别是省份
            LEVEL_PROVINCE -> {
                //传递当前选中的省份位置，查询相应的城市
                mImgBack.visibility = View.VISIBLE//显示反复按钮
                selectPresenter.queryCities(position)
            }
            //如果当前的是城市界面
            LEVEL_CITY -> {
                mImgBack.visibility = View.VISIBLE
                selectPresenter.queryCountries(position)
            }
            //如果当前选择的级别是县
            LEVEL_COUNT -> {
                selectPresenter.queryCoutry(position)
            }
        }
//        toast("点击了" + mDatas[position])
    }

}
