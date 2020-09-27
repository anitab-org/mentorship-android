package org.systers.mentorship.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkChecker {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return run {
                val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                networkInfo?.isConnected ?: false
            }
        }
    }
}