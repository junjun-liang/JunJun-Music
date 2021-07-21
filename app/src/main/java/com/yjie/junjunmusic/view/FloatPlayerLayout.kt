package com.yjie.junjunmusic.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.yjie.junjunmusic.R
import java.util.jar.Attributes

/**
 * 1：当我们点击屏幕时，就产生了点击事件，这个事件被封装成一个类：MotionEvent
 * 这个motionEvent产生后，，就会传递给view的层级，MotionEvent在view中层级传递过程
 * 就是点击事件的分发。
 * 2：事件首先传递给当前activity，这会调用activity的dispatchTouchEvent()的方法，具体是由
 * phoneWindow来完成，phoneWindow把事件给decorView, decorView将事件给根ViewGroup
 */

class FloatPlayerLayout : LinearLayout {

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initView(context)
    }

    constructor(context: Context, attributes: AttributeSet, defStyleAttr: Int): super(context, attributes, defStyleAttr){
        initView(context)
    }

    private fun initView(context: Context) {
        View.inflate(context, R.layout.play_float_layout, this)
        //居中显示
        gravity = Gravity.CENTER
        //设置阴影

    }

    /**
     * 用来进行事件分发
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 用来进行事件的拦截，在dispatchTouchEvent()中调用，需要注意的是VIEW没有提供该方法
     */
    private var moveY = 0f
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN->{
                moveY = ev.y
            }
            MotionEvent.ACTION_MOVE->{
                return true
            }
        }
        return false
    }

    /**
     * 用来处理点击事件。在dispatchTouchEvent()中调用
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }
}