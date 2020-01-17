package org.systers.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ChangePassword

/**
 * This class represents the [ViewModel] used for ChangePasswordFragment
 */
class ChangePasswordViewModel : BaseViewModel() {
    override var TAG = this::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    /**
     * Updates the password of the current user
     */
    fun changeUserPassword(changePassword: ChangePassword) =
        observe(userDataManager.updatePassword(changePassword))
}
