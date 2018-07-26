package org.systers.mentorship

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate

/**
 * Created by murad on 7/24/18.
 */

class MentorshipApplication : Application() {

    companion object {

        lateinit var instance: MentorshipApplication

        fun getApplication(): MentorshipApplication {
            return instance
        }

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
