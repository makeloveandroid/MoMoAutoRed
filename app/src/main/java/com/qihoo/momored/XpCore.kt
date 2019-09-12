@file:JvmName("XpCore")
package com.qihoo.momored

import android.util.Log
import com.qihoo.momored.hook.ActivityHook
import com.qihoo.momored.hook.RedHook
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class XpCore : IXposedHookLoadPackage {
    val PKG = "com.immomo.momo"
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName == PKG && lpparam.processName == PKG) {
            ActivityHook.hook(lpparam.classLoader)
            RedHook.hook(lpparam.classLoader)
        }

    }
}

