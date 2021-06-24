package com.yjie.architecture.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.yjie.architecture.nav.NavHostFragment

abstract class BaseVmFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity

    private var fragmentProvider: ViewModelProvider? = null
    private var activityProvider: ViewModelProvider? = null
    private var applicationProvider: ViewModelProvider? = null
    private var dataBindingConfig: DataBindingConfig? = null
    private var viewDataBinding: ViewDataBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as AppCompatActivity
        // 必须要在Activity与Fragment绑定后，因为如果Fragment可能获取的是Activity中ViewModel
        // 必须在onCreateView之前初始化viewModel，因为onCreateView中需要通过ViewModel与DataBinding绑定

    }

    /**
     * 通过activity获取viewModel，跟随activity生命周期
     */
    protected fun <T : ViewModel?> getActivityViewModel(modelClass: Class<T>): T? {
        if (activityProvider == null) {
            activityProvider = ViewModelProvider(mActivity)
        }
        return activityProvider?.get(modelClass)
    }

    /**
     * 通过fragment获取viewModel，跟随fragment生命周期
     */
    protected open fun <T : ViewModel?> getFragmentViewModel(modelClass: Class<T>): T? {
        if (fragmentProvider == null) {
            fragmentProvider = ViewModelProvider(this)
        }
        return fragmentProvider?.get(modelClass)
    }

    /**
     * 初始化viewModel
     * 之所以没有设计为抽象，是因为部分简单activity可能不需要viewModel
     * observe同理
     */
    open fun initViewModel() {

    }

    /**
     * 注册观察者
     */
    open fun observe() {

    }

    /**
     * fragment跳转
     */
    protected fun nav(): NavController {
        return NavHostFragment.findNavController(this)
    }

    /**
     * 点击事件
     */
    open fun onClick() {

    }

    /**
     * 设置状态栏背景颜色
     */
    open fun setStatusColor() {

    }

    /**
     * 沉浸式状态
     */
    open fun setSystemInvadeBlack() {

    }

    /**
     * 初始化View以及事件
     */
    open fun initView() {

    }

    /**
     * 加载数据
     */
    open fun loadData() {

    }

    /**
     * 初始化入口
     */
    abstract fun init(savedInstanceState: Bundle)

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId(): Int?

    /**
     * 获取dataBinding配置项
     */
    abstract fun getDataBindingConfig(): DataBindingConfig?
}