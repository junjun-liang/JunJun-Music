package com.yjie.junjunmusic.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.yjie.architecture.base.BaseFragment
import com.yjie.architecture.common.doSelected
import com.yjie.architecture.common.initFragment
import com.yjie.architecture.ui.page.DataBindingConfig
import com.yjie.junjunmusic.BR
import com.yjie.junjunmusic.PlayViewModel
import com.yjie.junjunmusic.R
import com.yjie.junjunmusic.play.bean.PlayerManager
import com.yjie.junjunmusic.ui.main.home.HomeFragment
import com.yjie.junjunmusic.ui.main.mime.MineFragment
import com.yjie.junjunmusic.ui.main.spare.SpareFragment
import com.yjie.junjunmusic.ui.main.tab.ArticleFragment
import com.yjie.junjunmusic.ui.main.tab.TabFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {
    private val fragments = arrayListOf<Fragment>()

    private val homeFragment by lazy { HomeFragment() }
    private val spareFragment by lazy { SpareFragment() }
    private val mineFragment by lazy { MineFragment() }
    private val tabFragment by lazy { TabFragment() }
    private val articleFragment by lazy { ArticleFragment() }

    private var playViewModel : PlayViewModel? = null

    init {
        fragments.apply {
            add(homeFragment)
            add(spareFragment)
            add(mineFragment)
            add(tabFragment)
            add(articleFragment)
        }
    }

    override fun init(savedInstanceState: Bundle?) {
        vpHome.initFragment(childFragmentManager, fragments).run {
            offscreenPageLimit = fragments.size
        }

        vpHome.doSelected {
            btnNav.menu.getItem(it).isChecked = true
        }

        btnNav.run {
            setOnNavigationItemSelectedListener { item->
                when(item.itemId){
                    R.id.menu_home -> vpHome.setCurrentItem(0, false)
                    R.id.menu_project -> vpHome.setCurrentItem(1, false)
                    R.id.menu_square -> vpHome.setCurrentItem(2, false)
                    R.id.menu_official_account -> vpHome.setCurrentItem(3, false)
                    R.id.menu_mine -> vpHome.setCurrentItem(4, false)
                }
                true
            }
        }
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

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onClick() {
        floatLayout.playClick {
            PlayerManager.INSTANCE.controlPlay()
        }
    }
}