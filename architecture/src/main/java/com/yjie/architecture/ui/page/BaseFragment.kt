package com.yjie.architecture.ui.page

import android.app.Activity
import android.app.Application
import android.os.Handler
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.kunminx.architecture.ui.page.DataBindingFragment

abstract class BaseFragment : DataBindingFragment() {

    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null

    private val mHandler = Handler()
    protected var mAnimationLoaded = false

    //TODO tip 1: DataBinding 严格模式（详见 DataBindingFragment - - - - - ）：
    // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
    // 通过这样的方式，来彻底解决 视图调用的一致性问题，
    // 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。

    // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

    //TODO tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控，
    //目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域，
    //值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个，
    //所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致。

    //如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/6257931840

    protected fun <T : ViewModel?> getFragmentScopeViewModel(viewModelClass: Class<T>): T? {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider?.get(viewModelClass)
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
            mApplicationProvider = ViewModelProvider(viewModelStore, getApplicationFactory(mActivity))
        }
        return mActivityProvider?.get(viewModelClass)
    }

    private fun getApplicationFactory(activity: Activity): ViewModelProvider.Factory {
        checkActivity(this)
        val application = getApplication(activity)
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    private fun getApplication(activity: Activity): Application {
        return activity.application
            ?: throw IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }

    private fun checkActivity(fragment: Fragment) {
        fragment.activity
            ?: throw IllegalStateException("Can't create ViewModelProvider for detached fragment")
    }

    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        mHandler.postDelayed({
            if (!mAnimationLoaded) {
                mAnimationLoaded = true
                loadInitData()
            }
        }, 280);
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    protected fun loadInitData(){

    }

    override fun initViewModel() {
        TODO("Not yet implemented")
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        TODO("Not yet implemented")
    }

}