package org.systers.mentorship

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication

/**
 * The entry point, a class that represents Mentorship application.
 */

class MentorshipApplication : MultiDexApplication() {

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
    }
}
