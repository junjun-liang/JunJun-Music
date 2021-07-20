package com.yjie.junjunmusic.ui.main.tab

import android.os.Bundle
import android.util.Log
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.R
import com.yjie.junjunmusic.BR

class ArticleFragment : BaseFragment() {
    private var articleViewModel: ArticleViewModel? = null

    override fun init(savedInstanceState: Bundle) {

    }

    override fun getLayoutId(): Int? = R.layout.fragment_article

    override fun initViewModel() {
        Log.d("junjunFragment", "articleFragment")
        articleViewModel = getActivityViewModel(ArticleViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig? {
        return DataBindingConfig(R.layout.fragment_article, articleViewModel)
            .addBindingParam(BR.vm, articleViewModel)
    }
}