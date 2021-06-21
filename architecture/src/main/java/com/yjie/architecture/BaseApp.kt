package com.yjie.architecture

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

open class BaseApp : Application(), ViewModelStoreOwner {

    private val mAppViewModelStore: ViewModelStore by lazy { ViewModelStore() }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }
}