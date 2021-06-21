package com.yjie.architecture.ui

import android.app.Activity
import android.app.Application
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.kunminx.architecture.ui.page.DataBindingActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjie.architecture.data.NetworkStateManager
import com.yjie.architecture.utils.BarUtils

abstract class BaseActivity: DataBindingActivity() {

    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.setStatusBarLightMode(this, true)
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(NetworkStateManager)
    }

    protected fun <T : ViewModel?> getActivityScopeViewModel(viewModelClass: Class<T>): T? {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this)
        }
        return mActivityProvider?.get(viewModelClass)
    }

    protected fun <T : ViewModel?> getApplicationScopeViewModel(viewModelClass: Class<T>): T? {
        if (mApplicationProvider == null) {
            val viewModelStore = ViewModelStore()
            mApplicationProvider = ViewModelProvider(viewModelStore, getApplicationFactory(this))
        }
        return mActivityProvider?.get(viewModelClass)
    }

    private fun getApplicationFactory(activity: Activity): ViewModelProvider.Factory {
        val application = getApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    private fun getApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        TODO("Not yet implemented")
    }

    override fun initViewModel() {
        TODO("Not yet implemented")
    }
}