package com.yjie.architecture.base

import android.os.Bundle
import android.widget.FrameLayout
import com.yjie.architecture.common.dip2px
import com.yjie.architecture.utils.StatusUtils
import com.yjie.architecture.view.LoadingTip

abstract class BaseLoadingActivity : BaseVmActivity() {
    private var mDecor: FrameLayout? = null
    var loadingTip: LoadingTip? = null

    override fun init(savedInstanceState: Bundle?) {
        mDecor = window.decorView as FrameLayout
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        params.topMargin = StatusUtils.getStatusBarHeight(this) + dip2px(this, 50f)
        params.bottomMargin = StatusUtils.getNavigationBarHeight(this)
        loadingTip = LoadingTip(this)
        mDecor?.addView(loadingTip, params)
        init2(savedInstanceState)
    }

    abstract fun init2(savedInstanceState: Bundle?)

}