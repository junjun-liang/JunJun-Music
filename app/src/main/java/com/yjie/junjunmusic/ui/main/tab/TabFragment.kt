package com.yjie.junjunmusic.ui.main.tab

import android.os.Bundle
import android.util.Log
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.R
import com.yjie.junjunmusic.BR

class TabFragment : BaseFragment() {
    private var tabViewModel: TabViewModel? = null

    override fun init(savedInstanceState: Bundle) {
    }

    override fun getLayoutId(): Int? = R.layout.fragment_tab

    override fun initViewModel() {
        Log.d("junjunFragment", "tabFragment")
        tabViewModel = getActivityViewModel(TabViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_tab, tabViewModel)
            .addBindingParam(BR.vm, tabViewModel)
    }
}