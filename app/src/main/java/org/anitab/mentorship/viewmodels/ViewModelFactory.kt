package org.anitab.mentorship.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.anitab.mentorship.remote.datamanager.UserDataManager

/**
 * Factory for ViewModels which depends upon [UserDataManager]
 */
class ViewModelFactory(private val userDataManager: UserDataManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        ChangePasswordViewModel::class.java -> ChangePasswordViewModel(userDataManager)
        HomeViewModel::class.java -> HomeViewModel(userDataManager)
        MemberProfileViewModel::class.java -> MemberProfileViewModel(userDataManager)
        MembersViewModel::class.java -> MembersViewModel(userDataManager)
        ProfileViewModel::class.java -> ProfileViewModel(userDataManager)
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}
