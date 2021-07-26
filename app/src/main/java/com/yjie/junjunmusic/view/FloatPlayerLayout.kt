package com.yjie.junjunmusic.view

import android.animation.ValueAnimator
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.yjie.architecture.common.albumById
import com.yjie.architecture.common.clickNoRepeat
import com.yjie.architecture.common.dip2px
import com.yjie.architecture.common.loadCircle
import com.yjie.architecture.utils.ScreenUtils
import com.yjie.junjunmusic.R
import kotlinx.android.synthetic.main.play_float_layout.view.*
import java.util.jar.Attributes

/**
 * 1：当我们点击屏幕时，就产生了点击事件，这个事件被封装成一个类：MotionEvent
 * 这个motionEvent产生后，，就会传递给view的层级，MotionEvent在view中层级传递过程
 * 就是点击事件的分发。
 * 2：事件首先传递给当前activity，这会调用activity的dispatchTouchEvent()的方法，具体是由
 * phoneWindow来完成，phoneWindow把事件给decorView, decorView将事件给根ViewGroup，即顶层
 * viewGroup。
 * 一般在事件传递只考虑viewGroup的onInterceptTouchEvent方法，因为一般情况下我们不会重写
 * dispatchTouchEvent方法。对于根viewGroup，点击事件首先传递给它的dispatchTouchEvent()方法
 * 如果viewGroup的onInterceptTouchEvent方法返回true，表示它拦截这个事件。这个事件就交给它的
 * onTouchEvent()方法处理。如果onInterceptTouchEvent方法返回false,它表示不拦截这个事件，则这个事件
 * 会交给它的子元素的dispatchTouchEvent()方法处理，如果反复下去，如果传递给底层的view，view是没有
 * 子view的，就会调用view的dispatchTouchEvent()方法，一般情况下最终会调用view的onTouchEvent()方法
 */

class FloatPlayerLayout : LinearLayout {

    private var isOpen = false

    /**
     * content应该展示的宽度
     */
    private val contentWidth = dip2px(context, 180f)

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
        onClick()
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
                //距离控件顶部的值
                moveY = ev.y
            }
            MotionEvent.ACTION_MOVE->{
                //返回true 拦截
                return true
            }
        }
        //不拦截
        return false
    }

    /**
     * 用来处理点击事件。在dispatchTouchEvent()中调用
     */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                var offsetY = translationY + (event.y - moveY)
                //因为translationY不管处于ViewGroup什么位置，初始值都为0，所以要买个top
                //最小值
                val offsetMin = dip2px(context,100f)
                //top view自身顶部到其父布局顶边的距离
                if (offsetY <  - top + offsetMin) {
                    offsetY =  - top.toFloat() + offsetMin
                }
                //最大值
                val screenHeight = ScreenUtils.getScreenHeight(context)
                val offsetMax = dip2px(context,150f)
                if (offsetY > screenHeight - top - offsetMax) {
                    offsetY = screenHeight - top.toFloat() - offsetMax
                }
                translationY = offsetY
                //返回true 消费move这个事件
                return true
            }
        }
        return super.onInterceptTouchEvent(event)
    }

    private fun onClick() {
        //音乐图片
        ivMusicPic.clickNoRepeat {
            //收缩状态进行展开动画
            if (!isOpen) {
                startAnim()
                ivMusicPic.isEnabled = false
                isOpen = true
            }
        }

        //x 号
        ivShrink.clickNoRepeat {
            //展开状态收缩
            if (isOpen) {
                startAnim()
                ivMusicPic.isEnabled = true
                isOpen = false
            }
        }
    }

    /**
     * 播放点击事件
     */
    fun playClick(onClick: (View) -> Unit) {
        ivPlaying.clickNoRepeat {
            onClick.invoke(it)
        }
    }

    /**
     * 悬浮窗点击事件
     */
    fun rootClick(onClick: (View) -> Unit) {
        root.clickNoRepeat {
            onClick.invoke(it)
        }
    }

    /**
     * 设置播放状态
     */
    fun setImgPlaying(isPlying: Boolean?) {
        isPlying?.apply {
            ivPlaying.isSelected = this
        }
    }

    /**
     * 设置歌名
     */
    fun setSongName(songName: String?) {
        if (TextUtils.isEmpty(songName)) {
            tvSongName.text = "暂无播放"
        } else {
            tvSongName.text = songName
        }
        songName?.apply {
        }
    }

    /**
     * 设置专辑图片
     */
    fun setAlbumPic(albumId: Long?) {
        //收到重置
        if (albumId == -1L) {
            ivMusicPic.setImageResource(R.drawable.svg_music_not)
            return
        }
        albumId?.apply {
            ivMusicPic.loadCircle(context.applicationContext, albumById(this))
        }
    }

    /**
     * 开启动画
     */
    private fun startAnim() {
        val animator = if (isOpen) {
            //从contentWidth宽度到0 变化
            ValueAnimator.ofInt(contentWidth, 0)
        } else {
            ValueAnimator.ofInt(0, contentWidth)
        }
        //播放时长,尽量与防点击抖动间隔一致
        animator.duration = 249
        animator.addUpdateListener {
            val value = it.animatedValue as Int
            //平滑的，动态的设置宽度
            val params = llContent.layoutParams as MarginLayoutParams
            params.width = value
            llContent.layoutParams = params
        }
        animator.start()
    }
}