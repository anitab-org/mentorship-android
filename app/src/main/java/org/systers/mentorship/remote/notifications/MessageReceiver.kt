package org.systers.mentorship.remote.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.systers.mentorship.R
import org.systers.mentorship.models.Notification
import org.systers.mentorship.room.NotificationsDatabase
import org.systers.mentorship.utils.Constants.NOTIFICATIONS_CHANNEL_ID
import org.systers.mentorship.utils.Constants.NOTIFICATION_ID
import org.systers.mentorship.utils.Constants.RC_NOTIFICATIONS
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.activities.NotificationsActivity
import java.util.*

class MessageReceiver : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.data["title"] ?: ""
        val message = remoteMessage.data["message"] ?: ""
        val creationTime = remoteMessage.data["creation_time"]?.toFloat() ?: 0f
        val type = remoteMessage.data["type"]?.toInt() ?: 0

        // make notification, save it and update the count of unread notifications
        val notification = Notification(UUID.randomUUID().toString(), title, message, creationTime, type)
        makeNotification(notification)
        PreferenceManager().addUnreadNotification()
        NotificationsDatabase.getInstance(this).notificationDao().addNotification(notification)
    }

    private fun makeNotification(notification: Notification) {
        val intent = Intent(this, NotificationsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, RC_NOTIFICATIONS, intent, PendingIntent.FLAG_ONE_SHOT)

        val notificationBuilder =
                NotificationCompat.Builder(this, NOTIFICATIONS_CHANNEL_ID)
                        .setContentTitle(notification.title)
                        .setContentText(notification.message)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

}