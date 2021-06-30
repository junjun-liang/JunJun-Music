package com.yjie.junjunmusic

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/28
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class PlayerService : Service() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}