package com.example.tonjies.weatherx.util

import android.content.Context

/**
 *
 */
object ShareUtils {

    val NAME="config"

    //存储
    fun putString(mContext:Context,key:String,value:String){
        val sharePreferense=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE)
        sharePreferense.edit().putString(key,value).apply()
    }

    //获取
    fun getString(mContext:Context,key:String,value:String):String{
        val sharePreferense=mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE)
        return sharePreferense.getString(key,value)
    }


}