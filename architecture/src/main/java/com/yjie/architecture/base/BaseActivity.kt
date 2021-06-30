package com.yjie.architecture.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.yjie.architecture.utils.Utils
import com.yjie.architecture.utils.ColorUtils
import com.yjie.architecture.utils.StatusUtils

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/16
 *     desc   : MVVM activity 基类
 *     version: 1.0
 * </pre>
 */
abstract class BaseActivity : AppCompatActivity() {

    private var mActivityProvider: ViewModelProvider? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLayoutId()?.let { setContentView(it) }
        setStatusColor()
        setSystemInvadeBlack()
        initViewModel()
        observe()
        init(savedInstanceState)
    }

    /**
     * 设置状态栏背景颜色
     */
    open fun setStatusColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#00ffffff"))
    }

    /**
     * 沉浸式状态
     */
    open fun setSystemInvadeBlack() {
        StatusUtils.setSystemStatus(this, true, true)
    }

    /**
     *  初始化ViewModel
     *  之所以没有设计为抽象，是因为部分简单activity可能不需要viewModel
     *  observe同理
     */
    open fun initViewModel() {}


    protected fun <T: ViewModel?> getActivityViewModel(modelClass: Class<T>) : T? {
        if (mActivityProvider == null){
            val viewModelStore = ViewModelStore()
            mActivityProvider = ViewModelProvider(viewModelStore, getAppFactory())
        }
        return mActivityProvider?.get(modelClass)
    }

    private fun getAppFactory(): ViewModelProvider.Factory{
        return ViewModelProvider.AndroidViewModelFactory.getInstance(Utils.getApp())
    }

    /**
     * 注册观察者
     */
    open fun observe() {}

    /**
     *  activity 入口
     */
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId(): Int?

}