package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.ChangePassword
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the [ViewModel] used for ChangePasswordFragment
 */
class ChangePasswordViewModel : BaseViewModel() {

    override var TAG: String = ChangePasswordViewModel::class.java.simpleName
    private val userDataManager: UserDataManager = UserDataManager()
    val successfulUpdate: MutableLiveData<Boolean> = MutableLiveData()

    /**
     * Updates the password of the current user
     */
    @SuppressLint("CheckResult")
    fun changeUserPassword(changePassword: ChangePassword) {
        userDataManager.updatePassword(changePassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer(
                        next = {
                            message = (it as CustomResponse).message
                            successfulUpdate.value = true
                        },
                        error = {successfulUpdate.value = false}
                ))
    }
}
