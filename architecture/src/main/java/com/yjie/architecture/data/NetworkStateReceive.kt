package com.yjie.architecture.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.yjie.architecture.utils.NetworkUtils
import com.yjie.architecture.common.toast

class NetworkStateReceive : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == p1?.action) {
            if (!NetworkUtils.isConnected()) {
                toast("网络不给力")
            }
        }
    }
}