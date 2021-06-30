package com.yjie.junjunmusic

import android.app.Application
import android.content.Context
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yjie.architecture.BaseApp
import com.yjie.architecture.utils.Utils

open class App : BaseApp() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Utils.init(this)

    }

    override fun onCreate() {
        super.onCreate()

        //MultiDex.install(this)
        initSmartHead()
    }

    /**
     * 初始化smart
     */
    private fun initSmartHead() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator{ context: Context, layout: RefreshLayout ->
            ClassicsHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator{ context: Context, layout: RefreshLayout ->
            ClassicsFooter(context)
        }
    }
}