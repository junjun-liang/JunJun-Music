package com.yjie.architecture.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

/**
 * <pre>
 *     author : yjie.liang
 *     time   : 2021/06/23
 *     desc   :
 *     version: 1.0
 * </pre>
 */

private fun Context.checkNetWorkState(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                true
            }
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                true
            }
            else -> {
                false
            }
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}