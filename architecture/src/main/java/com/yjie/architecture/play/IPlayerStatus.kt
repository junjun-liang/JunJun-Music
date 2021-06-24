package com.yjie.architecture.play


/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   : 播放状态,需要注入到播放控制器中,用于播放状态的回调
 *     version: 1.0
 * </pre>
 */
interface IPlayerStatus {
    /**
     * 缓存更新
     */
    fun onBufferingUpdate(present:Int)

    /**
     * 播放结束
     */
    fun onComplete()
}