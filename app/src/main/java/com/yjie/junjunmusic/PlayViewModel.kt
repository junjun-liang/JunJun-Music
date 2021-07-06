package com.yjie.junjunmusic

import android.provider.MediaStore
import androidx.databinding.ObservableField
import com.yjie.architecture.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import com.yjie.architecture.common.stringForTime
import com.yjie.junjunmusic.play.bean.AudioBean
import com.yjie.junjunmusic.play.bean.PlayerManager
import io.reactivex.internal.operators.observable.ObservableFilter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * des 关于播放viewModel,播放、播放列表、首页悬浮共用
 * @author zs
 */
class PlayViewModel : BaseViewModel() {

    val songName = ObservableField<String>("暂无播放")

    val singer = ObservableField<String>("")

    val albumPic = ObservableField<Long>()

    val playStatus = ObservableField<Int>()

    val playModePic = ObservableField<Int>(R.mipmap.ic_launcher)

    val listPlayModelPic = ObservableField<Int>(R.mipmap.play_order_gray)

    val playModeText = ObservableField<String>("列表循环")

    val maxDuration = ObservableField<String>("00:00")

    val currentDuration = ObservableField<String>("00:00")

    val maxProgress = ObservableField<Int>()

    val playProgress = ObservableField<Int>()

    val collect = ObservableField<Boolean>()

    /**
     * 重置
     */
    fun reset() {
        songName.set("")
        singer.set("")
        albumPic.set(-1)
        playStatus.set(PlayerManager.PAUSE)
        maxDuration.set("00:00")
        currentDuration.set("00:00")
        maxProgress.set(0)
        playProgress.set(0)
        collect.set(false)
    }

    fun setAudioBean(audioBean: AudioBean) {
        songName.set(audioBean.name)
        singer.set(audioBean.singer)
        maxDuration.set(stringForTime(audioBean.duration))
        maxProgress.set(audioBean.duration)
        albumPic.set(audioBean.albumId)
        viewModelScope.launch {
            val bean = withContext(Dispatchers.IO) {

            }
            collect.set(bean != null)
        }
    }





}