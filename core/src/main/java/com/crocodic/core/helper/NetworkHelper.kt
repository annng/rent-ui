package com.crocodic.core.helper

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkHelper {
    companion object {
        fun isWifiConnected(context: Application): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cm.getNetworkCapabilities(cm.activeNetwork)?.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            } else {
                cm.activeNetworkInfo?.type == ConnectivityManager.TYPE_WIFI
            } ?: false
        }
    }


}