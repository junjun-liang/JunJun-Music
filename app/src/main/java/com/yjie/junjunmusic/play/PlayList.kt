package com.yjie.junjunmusic.play

import android.os.Build
import androidx.annotation.RequiresApi
import com.yjie.architecture.common.getRandom
import com.yjie.architecture.common.isListEmpty
import com.yjie.architecture.common.toast
import com.yjie.architecture.utils.Utils
import com.yjie.junjunmusic.play.bean.AudioBean
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.R)
class PlayList private constructor() {

    companion object {
        val instance: PlayList by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PlayList()
        }
    }

    /**
     * 当前列表
     */
    private var currentPlayList = mutableListOf<AudioBean>()

    /**
     * 本地列表
     */
    private var _localPlayList = mutableListOf<AudioBean>()

    /**
     * 本地列表，只读
     */
    var localPlayList: List<AudioBean> = _localPlayList

    /**
     * 历史列表
     */
    private var _historyPlayList = mutableListOf<AudioBean>()
    var historyPlayList: List<AudioBean> = _historyPlayList

    /**
     * 收藏列表
     */
    private var _collectPlayList = mutableListOf<AudioBean>()
    var collectPlayList: List<AudioBean> = _collectPlayList

    /**
     * 播放模式
     */
    private var playMode = PlayMode.ORDER_PLAY_MODE

    /**
     * 列表类型
     */
    private var playListType = Type.LOCAL_LIST

    /**
     * 当前正在播放的音频 默认为null
     */
    private var currentAudio: AudioBean? = null

    /**
     * 当前播放音频对象在对应播放列表的角标
     */
    private var currentIndex = 0

    init {
        GlobalScope.launch {
            _localPlayList = readingLocalList(Utils.getApp())
            localPlayList = _localPlayList
            switchPlayList()
        }
    }

    private fun switchPlayList() {
        when (playListType) {
            Type.LOCAL_LIST -> {
                replacePlayList(_localPlayList)
                this.playListType = Type.LOCAL_LIST
            }
            Type.HISTORY_LIST -> {
                replacePlayList(_historyPlayList)
                this.playListType = Type.HISTORY_LIST
            }
            Type.COLLECT_LIST -> {
                replacePlayList(_collectPlayList)
                this.playListType = Type.COLLECT_LIST
            }
        }
    }

    /**
     * 替换播放列表
     */
    fun replacePlayList(playList: MutableList<AudioBean>) {
        currentPlayList.apply {
            clear()
            addAll(playList)
        }
    }

    /**
     * 获取当前音频
     */
    fun getCurrentAudio(): AudioBean? {
        return currentAudio
    }

    /**
     * 设置当前音频
     */
    fun setCurrentAudio(audioBean: AudioBean) {
        this.currentAudio = audioBean
    }

    /**
     * 清除当前音频
     */
    fun clear() {
        currentAudio = null
    }

    /**
     * 获取当前播放音频的index
     */
    fun getIndexByAudio(audioBean: AudioBean): Int {
        currentAudio = audioBean
        for (index in 0 until currentPlayList.size) {
            if (audioBean == currentPlayList[index]) {
                return index
            }
        }
        return 0
    }

    /**
     * 获取播放列表长度
     */
    fun getPlayListSize() : Int{
        return currentPlayList.size
    }

    /**
     * 获取当前列表
     */
    fun getPlayList() :MutableList<AudioBean>{
        return currentPlayList
    }

    /**
     * 播放音乐
     */
    fun startAudio() :AudioBean? {
        if (!isListEmpty(currentPlayList)) {
            return currentPlayList[0]
        }
        return currentAudio
    }

    /**
     * 上一首
     */
    fun previousAudio():AudioBean? {
        if (!isListEmpty(currentPlayList)) {
            when (playMode) {
                //顺序
                PlayMode.ORDER_PLAY_MODE -> {
                    currentIndex = if (currentIndex > 0) {
                        currentIndex - 1
                    } else {
                        currentPlayList.size - 1
                    }
                }
                //单曲(不做处理)
                PlayMode.SINGLE_PLAY_MODE -> {
                }
                //随机
                PlayMode.RANDOM_PLAY_MODE -> {
                    currentIndex = getRandom(0, currentPlayList.size - 1)
                }
            }
            currentAudio = currentPlayList[currentIndex]
        } else {
            //当前播放列表为空将丹铅播放置为null
            currentAudio = null
        }
        return currentAudio
    }

    /**
     * 下一个音频
     */
    fun nextAudio(): AudioBean? {
        if (!isListEmpty(currentPlayList)) {
            when (playMode) {
                //顺序
                PlayMode.ORDER_PLAY_MODE -> {
                    currentIndex = if (currentIndex < currentPlayList.size - 1) {
                        currentIndex + 1
                    } else {
                        0
                    }
                }
                //单曲(不做处理)
                PlayMode.SINGLE_PLAY_MODE -> {
                }
                //随机
                PlayMode.RANDOM_PLAY_MODE -> {
                    currentIndex = getRandom(0, currentPlayList.size - 1)
                }
            }
            currentAudio = currentPlayList[currentIndex]
        } else {
            //当前播放列表为空将丹铅播放置为null
            currentAudio = null
        }
        return currentAudio
    }

    /**
     * 获取当前播放模式
     */
    fun getCurrentPlayMode(): Int {
        return playMode
    }

    /**
     * 切换循环模式
     */
    fun switchPlayMode(): Int {
        when (playMode) {
            PlayMode.ORDER_PLAY_MODE -> {
                toast("顺序循环")
                playMode = PlayMode.ORDER_PLAY_MODE
            }
            PlayMode.SINGLE_PLAY_MODE -> {
                toast("单曲循环")
                playMode = PlayMode.SINGLE_PLAY_MODE
            }
            PlayMode.RANDOM_PLAY_MODE -> {
                toast("随机播放")
                playMode = PlayMode.RANDOM_PLAY_MODE
            }
        }
        return playMode
    }


    class PlayMode {
        companion object {
            /**
             * 顺序播放
             */
            const val ORDER_PLAY_MODE = 9

            /**
             * 单曲循环播放
             */
            const val SINGLE_PLAY_MODE = 99

            /**
             * 随机播放
             */
            const val RANDOM_PLAY_MODE = 999
        }
    }

    class Type {
        companion object {
            /**
             * 本地列表
             */
            const val LOCAL_LIST = 10

            /**
             * 收藏列表
             */
            const val COLLECT_LIST = 100

            /**
             * 历史列表
             */
            const val HISTORY_LIST = 1000
        }
    }
}