package org.systers.mentorship

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatDelegate
import org.systers.mentorship.utils.NetworkStateReceiver

/**
 * The entry point, a class that represents Mentorship application.
 */

class MentorshipApplication : Application() {

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

        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(NetworkStateReceiver(), intentFilter)

        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}
