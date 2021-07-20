package com.yjie.junjunmusic.ui

import android.os.Bundle
import android.util.Log
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.BR
import com.yjie.junjunmusic.PlayViewModel
import com.yjie.junjunmusic.R

class MainFragment : BaseFragment() {

    private var playViewModel : PlayViewModel? = null

    override fun init(savedInstanceState: Bundle) {
    }

    override fun getLayoutId(): Int? {
        return R.layout.fragment_main
    }

    override fun initViewModel() {
        Log.d("PlayFragment", "init view model")
        playViewModel = getActivityViewModel(PlayViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_main, playViewModel)
           .addBindingParam(BR.vm, playViewModel)
    }
}