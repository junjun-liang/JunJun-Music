package com.yjie.junjunmusic.ui.main.tab

import androidx.lifecycle.MutableLiveData
import com.yjie.architecture.base.BaseViewModel

class ArticleViewModel : BaseViewModel() {
    val content = MutableLiveData<String>("文章")
}