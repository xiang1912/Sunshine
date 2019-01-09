package com.example.tonjies.weatherx.util

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

object AppManger {
    private val activityStack: Stack<Activity> = Stack()

    /**
     * Activity入栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * Activity出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 清理栈
     */
    fun finishActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun exitApp(context: Context) {
        finishActivity()
        val activityManger = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManger.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}