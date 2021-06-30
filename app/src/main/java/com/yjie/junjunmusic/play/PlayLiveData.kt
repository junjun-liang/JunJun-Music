package com.yjie.junjunmusic.play

import androidx.lifecycle.MutableLiveData
import com.yjie.junjunmusic.play.bean.AudioBean
import com.yjie.junjunmusic.play.bean.ProgressBean

/**
 * 用于分发，存储音乐的状态
 */
class PlayLiveData {

    /**
     * 歌曲信息
     */
    val audioLiveData = MutableLiveData<AudioBean>()

    /**
     * 播放状态，目前四种状态，
     * release
     * start
     * resume
     * pause
     */
    val playStatusLiveData = MutableLiveData<Int>()

    /**
     * 当前播放进度
     */
    val progressLiveData = MutableLiveData<ProgressBean>()

    /**
     * 播放模式
     */
    val playModeLiveData = MutableLiveData<Int>()

    /**
     * 重置
     */
    val resetLiveData = MutableLiveData<Int>()
}