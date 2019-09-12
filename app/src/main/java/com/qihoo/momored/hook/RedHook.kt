package com.qihoo.momored.hook

import android.os.Message
import android.view.View.X
import com.qihoo.momored.hookFun
import com.qihoo.momored.log
import de.robv.android.xposed.XposedHelpers


object RedHook {
    fun hook(classLoader: ClassLoader) {
        hookMessage(classLoader)
    }
    private fun hookMessage(classLoader: ClassLoader) {
        hookFun("com.immomo.momo.MomoApplication\$1", classLoader, "handleMessage", Message::class.java,
                beforeHookedMethod = {
                    val data = it.args[0] as Message
                    if (data.what == 954) {
                        log("==============收到消息了(开始)===========")
                        val msgType = data.obj.toString()
                        log("消息类型$msgType")
                        val message = data.data
                        message?.keySet()?.forEach {
                            log("$it  :  ${message[it]}")
                        }
                        log("==============收到消息了(结束)===========")
                    }
                })
    }
}