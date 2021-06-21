package com.yjie.junjunmusic

import android.app.Application
import android.content.Context
import com.yjie.architecture.BaseApp
import com.yjie.architecture.utils.Utils

open class App : BaseApp() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Utils.init(this)
    }

}