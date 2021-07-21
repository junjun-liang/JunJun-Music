package com.yjie.architecture.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.yjie.architecture.nav.NavHostFragment
import com.yjie.architecture.ui.page.DataBindingConfig

abstract class BaseFragment : Fragment() {

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
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutId()?.let {
            setStatusColor()
            setSystemInvadeBlack()
            //获取ViewDataBinding
            val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, it, container, false)
            //将ViewDataBinding生命周期与Fragment绑定
            binding.lifecycleOwner = viewLifecycleOwner
            dataBindingConfig = getDataBindingConfig()
            dataBindingConfig?.apply {
                val bindingParams = bindingParams
                // 将bindingParams逐个加入到ViewDataBinding中的Variable
                // 这一步很重要,否则xml中拿不到variable中内容
                for (i in 0 until bindingParams.size()) {
                    binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
                }
            }
            viewDataBinding = binding
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init(savedInstanceState)
        //observe一定要在初始化最后，因为observe会收到黏性事件，随后对ui做处理
        observe()
        onClick()
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
    abstract fun init(savedInstanceState: Bundle?)

    /**
     * 获取layout布局
     */
    abstract fun getLayoutId(): Int?

    /**
     * 获取dataBinding配置项
     */
    abstract fun getDataBindingConfig(): DataBindingConfig?
}