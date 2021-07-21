package com.yjie.junjunmusic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yjie.architecture.base.BaseLoadingActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 一个activity包含一个window对象(new phoneWindows().setContentView())，
 * 这个对象是由phoneWindow来实现的。PhoneWindow将DecorView作为一个应用窗口的根view(generateDecor())，
 * 而这个decorView又将屏幕划分为两个区域，titleView和contentView，而我们平常为应用所写的布局正是
 * 展示在contentView中的
 *
 */

/**
 * des 主页面，作用有二
 *     1.用于承载Fragment
 *     2.作为音频播放观察者,接受到通知立即更新viewModel内状态
 *       间接通过DataBinding更新View
 */

class MainActivity : BaseLoadingActivity() {

    override fun init2(savedInstanceState: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayoutId(): Int = R.layout.activity_main
}