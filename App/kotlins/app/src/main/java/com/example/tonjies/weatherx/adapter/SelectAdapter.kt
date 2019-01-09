package com.example.tonjies.weatherx.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tonjies.weatherx.R

class SelectAdapter : RecyclerView.Adapter<SelectAdapter.ViewHolder> {


    //上下文
    private var context: Context? = null
    //数据
    private var mList = ArrayList<String>()

    /**
     * 空参构造方法
     */
    constructor() {

    }

    constructor(context: Context, list: ArrayList<String>) {
        this.context = context
        this.mList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return ViewHolder(view)
    }

    /**
     * 声明控件
     */
    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        //城市标题
        var city: TextView = item.findViewById(R.id.mTxtCityName)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cityName = mList[position]
        holder.city.text = cityName
    }
}