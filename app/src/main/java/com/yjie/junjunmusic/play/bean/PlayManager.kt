package com.yjie.junjunmusic.play.bean

import android.os.Build
import androidx.annotation.RequiresApi
import com.yjie.architecture.play.IPlayer
import com.yjie.architecture.play.IPlayerStatus
import com.yjie.architecture.play.MediaPlayerHelper
import com.yjie.junjunmusic.play.PlayList
import com.yjie.junjunmusic.play.PlayLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import io.reactivex.disposables.Disposable
import java.util.*

/**
 * des 音频管理
 *     通过单例模式实现,托管音频状态与信息,并且作为唯一的可信源
 *     通过观察者模式统一对状态进行分发
 *     实则是一个代理,将目标对象Player与调用者隔离,并且在内部管理观察者
 */


class PlayManager private constructor() : IPlayerStatus {

    companion object {
        val instance: PlayManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PlayManager()
        }

        //播放器状态,当前共4种,可在此处随时扩展
        /**
         * 重置
         */
        const val RELEASE = 100

        /**
         * 从头开始播放
         */
        const val START = 200

        /**
         * 播放
         */
        const val RESUME = 300

        /**
         * 暂停
         */
        const val PAUSE = 400
    }

    /**
     * 用于分发和存储音乐状态的LiveData, 目前有三处会注册
     * 1，播放界面
     * 2，悬浮窗
     * 3，通知栏
     */
    val playLiveData = PlayLiveData()

    private val playerHelper: IPlayer = MediaPlayerHelper()

    /**
     * 用于关闭RXJava
     */
    private var disposable: Disposable? = null

    /**
     * 播放状态
     */
    private var playStatus = RELEASE

    /**
     * 播放列表
     */
    private var playList: PlayList = PlayList.instance

    init {
        playerHelper.setPlayStatus(this)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun startTimer() {
        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                //仅当播放状态的时候通知观察者
                if (playerHelper.isPlaying()) {
                    sendProgressToObserver(playerHelper.getProgress())
                }
            }

    }


    /**
     * 与播放按钮相对应,点击播放按钮存在三种场景
     * 1.播放器还未被初始化: 播放第一首音频,与start()对应
     * 2.暂停状态: 切换为播放状态,与resume()对应
     * 3.播放状态: 切换为暂停状态,与pause()对应
     *
     * 此方法意图是将所有播放逻辑全部控制在内部,视图层不做任何逻辑处理,只专注于事件监听与ui渲染
     **/
    @RequiresApi(Build.VERSION_CODES.R)
    fun controlPlay() {
        if (playList.getCurrentAudio() == null) {

        } else {
            if (playerHelper.isPlaying()) {

            }
        }
    }

    /**
     * 播放一个新的音频
     */
    fun play(audioBean: AudioBean?) {
        if (audioBean == null) {
            playerHelper.reset()
        } else {

        }
    }

    /**
     * 跳转到指定位置
     */
    fun seekTo(duration: Int) {
        playerHelper.seekTo(duration)
    }

    /**
     * 切换播放模式
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun switchPlayMode() {
        sendPlayModeToObserver(playList.switchPlayMode())
    }

    /**
     * 获取当前音频
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun getCurrentAudio():AudioBean? {
        return playList.getCurrentAudio()
    }

    /**
     * 获取播放列表大小
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun getPlayListSize():Int {
        return playList.getPlayListSize()
    }

    /**
     * 获取播放列表
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun getPlayList(): MutableList<AudioBean> {
        return playList.getPlayList()
    }

    /**
     * 重置并释放播放器
     */
    @RequiresApi(Build.VERSION_CODES.R)
    fun clear() {
        disposable?.dispose()
        playList.clear()
        playerHelper.reset()
        playerHelper.release()
        playList.clear()
    }

    /**
     * 给观察者发送音乐信息
     */
    private fun sendAudioToObserver(audioBean: AudioBean) {
        playLiveData.audioLiveData.value = audioBean
    }

    /**
     * 给观察者发送进度
     */
    @RequiresApi(Build.VERSION_CODES.R)
    private fun sendProgressToObserver(duration: Int) {
        playList.getCurrentAudio()?.duration?.let {
            playLiveData.progressLiveData.value = ProgressBean(duration, it)
        }
    }

    /**
     * 给观察者发送播放模式
     */
    private fun sendPlayModeToObserver(playMode: Int) {
        playLiveData.playModeLiveData.value = playMode
    }

    /**
     *  给观察者发送重置信号
     */
    private fun sendResetToObserver() {
        playLiveData.resetLiveData.value = 0
    }

    override fun onBufferingUpdate(present: Int) {
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onComplete() {
        playList.nextAudio()?.let { }
    }

}