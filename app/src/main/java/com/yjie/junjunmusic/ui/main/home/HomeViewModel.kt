package com.yjie.junjunmusic.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yjie.architecture.base.BaseViewModel

class HomeViewModel:BaseViewModel() {
    val content = MutableLiveData<String>("主页")
}