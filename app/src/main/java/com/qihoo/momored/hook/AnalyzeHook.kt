package com.qihoo.momored.hook

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.qihoo.momored.hookFun
import com.qihoo.momored.log
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers

// 分析hook类,主要辅助分析功能

/**
 * Activity hook 信息
 */
object ActivityHook {
    fun hook(classLoader: ClassLoader) {
        hookOnCreate(classLoader)
        hookOnResume(classLoader)
    }

    /**
     * hook activity 的oncreate方法
     */
    fun hookOnCreate(classLoader: ClassLoader) {
        hookFun(Activity::class.java, "onCreate", Bundle::class.java,
                afterHookedMethod = {
                    // 输出 intent 中的携带数据以供观察
                    val ac = it.thisObject as Activity
                    val extras = ac.intent.extras
                    log("=========${ac.javaClass.name}==========开始  onCreate")
                    extras?.keySet()?.forEach { key ->
                        log("$key : ${extras[key]}")
                    }
                    log("=========${ac.javaClass.name}==========结束  onCreate")
                })
    }


    /**
     * hook activity 的onResume方法
     */
    fun hookOnResume(classLoader: ClassLoader) {
        hookFun(Activity::class.java, "onResume",
                beforeHookedMethod = {
                    val ac = it.thisObject as Activity
                    log("=========${ac.javaClass.name}==========开始前  OnResume")
                },
                afterHookedMethod = {
                    val ac = it.thisObject as Activity
                    log("=========${ac.javaClass.name}==========开始后  OnResume")
                })

    }
}

