package org.systers.mentorship.remote.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.google.firebase.iid.FirebaseInstanceId
import org.systers.mentorship.R
import org.systers.mentorship.utils.Constants
import org.systers.mentorship.utils.Constants.CHANNEL_DESCRIPTION
import org.systers.mentorship.utils.Constants.CHANNEL_NAME

class NotificationsUtils(private val context: Context) {

    init {
        sendFirebaseTokenToBackend()
        createNotificationChannel()
    }

    private fun sendFirebaseTokenToBackend() {
        // TODO: Send token to backend
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Toast.makeText(context, R.string.error_something_went_wrong, Toast.LENGTH_SHORT).show()
            } else {
                val token = task.result?.token
                // in order to use via Postman
                println(token)
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    Constants.NOTIFICATIONS_CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}