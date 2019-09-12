package com.qihoo.momored

import android.util.Log
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import java.util.*

// 封装下 xp框架
fun log(msg: String) = Log.d("wyz", msg)

fun hookFun(clazz: Class<*>, methodName: String, vararg parameterTypes: Any,
            beforeHookedMethod: (param: XC_MethodHook.MethodHookParam) -> Unit = {},
            afterHookedMethod: (param: XC_MethodHook.MethodHookParam) -> Unit = {}) {

    val parameterTypesAndCallback = arrayOfNulls<Any>(parameterTypes.size + 1)
    parameterTypes.forEachIndexed { index, any ->
        parameterTypesAndCallback[index] = any
    }
    parameterTypesAndCallback[parameterTypes.size] = object :
            XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam?) {
            super.beforeHookedMethod(param)
            param?.let(beforeHookedMethod)
        }

        override fun afterHookedMethod(param: MethodHookParam?) {
            super.afterHookedMethod(param)
            param?.let(afterHookedMethod)
        }
    }
    XposedHelpers.findAndHookMethod(clazz, methodName, *parameterTypesAndCallback)
}

fun hookFun(className: String, classLoader: ClassLoader, methodName: String, vararg parameterTypes: Any,
            beforeHookedMethod: (param: XC_MethodHook.MethodHookParam) -> Unit = {},
            afterHookedMethod: (param: XC_MethodHook.MethodHookParam) -> Unit = {}) {

    val parameterTypesAndCallback = arrayOfNulls<Any>(parameterTypes.size + 1)
    parameterTypes.forEachIndexed { index, any ->
        parameterTypesAndCallback[index] = any
    }
    parameterTypesAndCallback[parameterTypes.size] = object :
            XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam?) {
            super.beforeHookedMethod(param)
            param?.let(beforeHookedMethod)
        }

        override fun afterHookedMethod(param: MethodHookParam?) {
            super.afterHookedMethod(param)
            param?.let(afterHookedMethod)
        }
    }
    XposedHelpers.findAndHookMethod(className, classLoader, methodName, *parameterTypesAndCallback)
}
