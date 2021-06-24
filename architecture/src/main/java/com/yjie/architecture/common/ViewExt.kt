package com.yjie.architecture.common

import android.content.ClipboardManager
import android.content.Context
import android.content.res.TypedArray
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   : 视图扩展方法
 *     version: 1.0
 * </pre>
 */

/**
 * viewPager2适配fragment
 */
fun ViewPager2.initFragment(fragment: Fragment, fragments: MutableList<Fragment>): ViewPager2 {
    adapter = object : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
    return this
}

fun ViewPager.initFragment(manager: FragmentManager, fragments: MutableList<Fragment>): ViewPager {
    adapter = object : FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment = fragments[position]
        override fun getCount(): Int = fragments.size
        override fun saveState(): Parcelable? {
            return null
        }
    }
    return this
}

fun ViewPager.doSelected(selected: (Int) -> Unit) {
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            selected.invoke(position)
        }
    })
}

/**
 * vararg关键字，允许你声明一个带有任意个参数的函数
 * 防止重复点击,可同时注册多个view
 */
fun setNoRepeatClick(vararg views: View, interval: Long = 400, onClick: (View) -> Unit) {
    views.forEach {
        it.clickNoRepeat(interval = interval) {view ->
            onClick.invoke(view)
        }
    }
}

/**
 * 防止重复点击
 * @param interval 重复间隔
 * @param onClick  事件响应
 */

var lastTime = 0L
fun View.clickNoRepeat(interval: Long = 400, onClick: (View) -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (lastTime != 0L && (currentTime - lastTime < interval)) {
            return@setOnClickListener
        }
        lastTime = currentTime
        onClick(it)
    }
}

/**
 * 复制剪切板
 */
fun copy(context:Context, msg:String){
    val clip = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clip.text = msg
    toast("已复制")
}

/**
 * 隐藏刷新加载ui
 */
fun SmartRefreshLayout.smartDismiss() {
    finishRefresh(0)
    finishLoadMore(0)
}

/**
 * 配置SmartRefreshLayout
 */
fun SmartRefreshLayout.smartConfig(){
    //加载
    setEnableLoadMore(true)
    //刷新
    setEnableRefresh(true)
    //不满一页关闭加载

    //滚动回弹
    setEnableOverScrollDrag(true)
}

/**
 * 获取当前主图颜色属性
 */

fun Context.getThemeColor(attr:Int):Int{
    val array:TypedArray = theme.obtainStyledAttributes(
        intArrayOf(attr)
    )
    val color = array.getColor(0, -0X50506)
    array.recycle()
    return color
}