package org.systers.mentorship

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

/**
 * The entry point, a class that represents Mentorship application.
 */

class MentorshipApplication : Application() {
    private val TAG = this::class.java.simpleName

    companion object {
        lateinit var instance: MentorshipApplication

        /**
         * @return the instance of the Application
         */
        fun getApplication(): MentorshipApplication {
            return instance
        }

        /**
         * @return the context of the Application
         */
        fun getContext(): Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        createNotificationChannel()
        logFirebaseInstanceId()
        listenToTopics()
    }

    /**
     * Listens to FCM "requests" topic, whose purpose is to handle all request-related notifications
     */
    private fun listenToTopics() {
        FirebaseMessaging.getInstance().subscribeToTopic("requests").addOnCompleteListener { task ->
            var msg = "Successfully subscribed"
            if (!task.isSuccessful) {
                msg = "Subscription failure"
            }
            Log.d(TAG, msg)
        }

    }

    /**
     * Logs Firebase token to the logcat. It's easy to copy it from there and paste to
     * console for debug purposes.
     */
    private fun logFirebaseInstanceId() {
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "getInstanceId failed", task.exception)
            } else {
                val token = task.result?.token

                Log.d(TAG, "getInstanceId success. Token: $token")
            }
        }
    }

    /**
     * Creates the [NotificationChannel] on API 26+
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(getString(R.string.channel_id), name, importance)
            channel.description = description
            // Registering the channel with the system; you can't change the importance
            // Importance or other notification behaviors are unchangeable after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
