package org.anitab.mentorship

import org.anitab.mentorship.local.UserDatabase
/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    /**
     * Provides context of [MentorshipApplication]
     */
    private fun provideApplicationContext() = MentorshipApplication.getContext()

    /**
     * Provides an instance of [UserDatabase]
     */
    fun provideUserDatabase(): UserDatabase = UserDatabase.getInstance(provideApplicationContext())
}
