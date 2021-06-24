package com.yjie.architecture.play

import android.media.MediaPlayer
import android.media.MediaPlayer.*
import android.util.Log
import com.yjie.architecture.common.toast

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MediaPlayerHelper : IPlayer,
    OnCompletionListener,
    OnBufferingUpdateListener,
    OnErrorListener,
    OnPreparedListener{

    private val mediaPlayer by lazy { MediaPlayer() }
    private var iPlayerStatus: IPlayerStatus? = null

    init {
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.setOnBufferingUpdateListener(this)
        mediaPlayer.setOnErrorListener(this)
        mediaPlayer.setOnPreparedListener(this)
    }

    override fun setPlayStatus(iPlayerStatus: IPlayerStatus) {
        this.iPlayerStatus = iPlayerStatus
    }

    override fun play(path: String) {
        mediaPlayer.reset()
        kotlin.runCatching {
            mediaPlayer.setDataSource(path)
        }.onSuccess {
            mediaPlayer.prepare()
        }.onFailure {
            Log.i("error", "it：${it.printStackTrace()}")
            toast("无效文件")
        }
    }

    override fun resume() {
        mediaPlayer.start()
    }

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun stop() {
        mediaPlayer.stop()
    }

    override fun seekTo(duration: Int) {
        mediaPlayer.seekTo(duration)
    }

    override fun reset() {
        mediaPlayer.reset()
    }

    override fun release() {
        mediaPlayer.release()
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer.isPlaying
    }

    override fun getProgress(): Int {
        return mediaPlayer.currentPosition
    }

    override fun onCompletion(p0: MediaPlayer?) {
        iPlayerStatus?.onComplete()
    }

    override fun onBufferingUpdate(p0: MediaPlayer?, p1: Int) {
        iPlayerStatus?.onBufferingUpdate(p1)
    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        return true
    }

    override fun onPrepared(p0: MediaPlayer?) {
        mediaPlayer.start()
    }
}