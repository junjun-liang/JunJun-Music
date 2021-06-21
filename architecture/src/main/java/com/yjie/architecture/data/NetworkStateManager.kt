package com.yjie.architecture.data

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.core.net.ConnectivityManagerCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.yjie.architecture.utils.Utils

object NetworkStateManager : DefaultLifecycleObserver {

    var networkStateReceive: NetworkStateReceive? = null

    override fun onResume(owner: LifecycleOwner) {
        networkStateReceive = NetworkStateReceive()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        Utils.getApp().registerReceiver(networkStateReceive, filter)
    }

    override fun onPause(owner: LifecycleOwner) {
        Utils.getApp().unregisterReceiver(networkStateReceive)
    }
}