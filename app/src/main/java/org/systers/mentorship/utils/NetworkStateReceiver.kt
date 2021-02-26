package org.systers.mentorship.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.MutableLiveData

class NetworkStateReceiver : BroadcastReceiver() {
    companion object {
        var isOnline = MutableLiveData<Boolean>()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val connMgr = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        isOnline.value = networkInfo != null && networkInfo.isConnected == true
    }
}