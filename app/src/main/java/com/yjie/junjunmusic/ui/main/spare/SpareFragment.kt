package com.yjie.junjunmusic.ui.main.spare

import android.os.Bundle
import android.util.Log
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.R
import com.yjie.junjunmusic.BR


class SpareFragment:BaseFragment() {
    private var spareViewModel : SpareViewModel? = null

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int? = R.layout.fragment_square

    override fun initViewModel() {
        Log.d("junjunFragment", "spareFragment")
        spareViewModel = getActivityViewModel(SpareViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_square, spareViewModel)
            .addBindingParam(BR.vm, spareViewModel)
    }
}