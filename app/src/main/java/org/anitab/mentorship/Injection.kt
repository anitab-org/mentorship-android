package org.anitab.mentorship

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import org.anitab.mentorship.local.UserDatabase
import org.anitab.mentorship.remote.ApiManager
import org.anitab.mentorship.remote.datamanager.UserDataManager
import org.anitab.mentorship.viewmodels.ViewModelFactory

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Creates an instance of [UserDataManager] based on the [ApiManager] and a
     * [UserDatabase] - local cache
     */
    private fun provideUserDataManager(context: Context): UserDataManager =
        UserDataManager(ApiManager.instance, UserDatabase.getInstance(context), Dispatchers.IO)

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * ViewModel objects.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideUserDataManager(context))
    }
}
