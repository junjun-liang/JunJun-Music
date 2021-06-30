package com.yjie.architecture.base

import android.os.Bundle

abstract class BaseLazyFragment: BaseFragment() {

    private var isLoaded = false

    override fun onResume(){
        super.onResume()
        if (!isLoaded && !isHidden) {
            isLoaded = true
            lazyInit()
        }
    }

    override fun init(savedInstanceState: Bundle) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}