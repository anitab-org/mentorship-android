package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] used for SettingsActivity
 */
class SettingsViewModel : ViewModel() {

    private val userDataManager: UserDataManager = UserDataManager()
    val successfulDelete: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    @SuppressLint("CheckResult")
    fun deleteUser() {
        userDataManager.deleteUser()
                .process { customResponse, throwable ->
                    when (throwable) {
                        null -> {
                            when (customResponse) {
                                null -> {
                                    successfulDelete.postValue(false)
                                }
                                else -> {
                                    message = customResponse.message
                                    successfulDelete.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = customResponse!!.message
                            successfulDelete.postValue(false)
                        }
                    }
                }
    }

}