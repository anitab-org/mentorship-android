package org.systers.mentorship.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData

class NetworkStateReceiver : BroadcastReceiver() {

    companion object {
        val isOnline = MutableLiveData<Boolean>()
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        isOnline.value = netInfo != null && netInfo.isConnected
    }

}