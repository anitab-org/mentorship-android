package org.systers.mentorship

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.CredentialsClient

/**
 * The entry point, a class that represents Mentorship application.
 */

class MentorshipApplication : Application() {

    companion object {

        lateinit var instance: MentorshipApplication
        lateinit var credentialsClient: CredentialsClient

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

        /*
             CredentialsClient instance that could be used in the whole app
             needed to use SmartLock
        */
        credentialsClient = Credentials.getClient(this)
    }
}
