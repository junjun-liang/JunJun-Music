package com.yjie.junjunmusic.ui.main.mime

import android.os.Bundle
import android.util.Log
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.R
import com.yjie.junjunmusic.BR

class MineFragment:BaseFragment() {
    private var mineViewModel: MineViewModel? = null

    override fun init(savedInstanceState: Bundle) {
    }

    override fun getLayoutId(): Int? = R.layout.fragment_mine

    override fun initViewModel() {
        Log.d("junjunFragment", "mineFragment")
        mineViewModel = getActivityViewModel(MineViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_mine, mineViewModel)
            .addBindingParam(BR.vm, mineViewModel)
    }
}