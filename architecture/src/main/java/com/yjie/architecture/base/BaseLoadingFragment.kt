package com.yjie.architecture.base

import android.content.Context
import android.view.ViewGroup
import com.yjie.architecture.utils.StatusUtils
import com.yjie.architecture.view.LoadingTip

abstract class BaseLoadingFragment: BaseVmFragment() {

    protected var loadingTip: LoadingTip? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseLoadingActivity) {
            loadingTip = context.loadingTip
        }
    }

    /**
     * 设置loadingView上下变局
     */
    protected fun setLoadingMargin(topMargin: Int, bottomMargin: Int) {
        val loadMarginTop = StatusUtils.getStatusBarHeight(mActivity) +topMargin
        val loadMarginBottom =  StatusUtils.getNavigationBarHeight(mActivity) + bottomMargin
        val params = loadingTip?.layoutParams as ViewGroup.MarginLayoutParams
        params.topMargin = loadMarginTop
        params.bottomMargin = loadMarginBottom
        loadingTip?.layoutParams = params
    }
}