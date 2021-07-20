package com.yjie.junjunmusic.ui.main.mime

import androidx.lifecycle.MutableLiveData
import com.yjie.architecture.base.BaseViewModel

class MineViewModel:BaseViewModel() {
    val content = MutableLiveData<String>("我的")
}