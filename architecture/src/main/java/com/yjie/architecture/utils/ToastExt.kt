package com.yjie.architecture.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * des Toast工具类
 * @date 2020/5/14
 * @author zs
 */

fun Context.toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    Toast.makeText(this, content, duration).apply { show() }
}

fun Context.toast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), duration)
}

fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(content)) return
    Utils.getApp().toast(content, duration)
}

fun longToast(content: String, duration: Int = Toast.LENGTH_SHORT){
    if (TextUtils.isEmpty(content)) return
    Utils.getApp().toast(content, duration)
}

fun longToast(@StringRes id: Int, duration: Int = Toast.LENGTH_SHORT){
    Utils.getApp().toast(id, duration)
}