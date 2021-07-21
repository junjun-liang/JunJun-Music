package com.yjie.junjunmusic.ui.main.home

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.R
import com.yjie.junjunmusic.BR


class HomeFragment : BaseFragment(){

    private var homeViewModel: HomeViewModel? = null

    override fun init(savedInstanceState: Bundle?) {
    }

    override fun initViewModel() {
        Log.d("junjunFragment", "homeFragment")
        homeViewModel = getActivityViewModel(HomeViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_home, homeViewModel)
           .addBindingParam(BR.vm, homeViewModel)
    }
}