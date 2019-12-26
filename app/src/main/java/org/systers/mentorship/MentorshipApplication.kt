package org.systers.mentorship

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.work.*
import org.systers.mentorship.utils.TokenWorker
import java.util.concurrent.TimeUnit

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

    private val uniqueWorkName = "${MentorshipApplication::class.java.simpleName}:refresh-token-work"

    override fun onCreate() {
        super.onCreate()

        startPeriodicRefreshTokenTask()

        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    // This method executes the TokenWorker class every hour including the first time it is called.
    private fun startPeriodicRefreshTokenTask() {
        val workConstraints = Constraints
                .Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
        val periodicRefreshTokenWork = PeriodicWorkRequest.Builder(
                TokenWorker::class.java,
                1,
                TimeUnit.HOURS
        ).setConstraints(workConstraints)
                .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                uniqueWorkName,
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicRefreshTokenWork
        )
    }
}
