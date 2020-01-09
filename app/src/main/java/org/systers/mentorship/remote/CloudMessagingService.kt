package org.systers.mentorship.remote

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.systers.mentorship.R
import org.systers.mentorship.db.AppDatabase
import org.systers.mentorship.db.Notification
import org.systers.mentorship.view.activities.MainActivity
import org.systers.mentorship.view.activities.START_FRAGMENT

/**
 * Service receiving FCM messages when the app is in foreground.
 * More info: https://firebase.google.com/docs/cloud-messaging/android/client
 */
class CloudMessagingService : FirebaseMessagingService() {
    private val TAG = this::class.java.simpleName

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]
        val typeCode = remoteMessage.data["type"]

        val code = Notification.NotificationType.fromNotificationTypeCode(typeCode)

        makeNotification(title, message, code)
        saveToDatabase(title, message, code)
    }

    /**
     * Saves Notification to local SQLite database.
     */
    private fun saveToDatabase(title: String?, message: String?, typeCode: Notification.NotificationType) {
        val db = AppDatabase.getInstance(applicationContext)

        val notification = Notification(System.currentTimeMillis(), title, message, typeCode)

        db.notificationDao().insertNotification(notification)
    }

    /**
     * Creates a notification using data provided by FCM message
     */
    private fun makeNotification(title: String?, message: String?, typeCode: Notification.NotificationType) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(START_FRAGMENT, typeCode.symbolicName)
        }

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder =
            NotificationCompat.Builder(this, getString(R.string.channel_id))
                .setStyle(NotificationCompat.BigTextStyle()
                .bigText(message))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setContentTitle(title)
                .setContentText(message)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        notificationManager.notify(0, notificationBuilder.build())
    }
}
