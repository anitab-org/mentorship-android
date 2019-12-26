package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.dsl.handleNetworkExceptionWithMessage
import org.systers.mentorship.remote.datamanager.AuthDataManager
import org.systers.mentorship.remote.requests.Register

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class SignUpViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val authDataManager: AuthDataManager = AuthDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    fun register(register: Register) = viewModelScope.launch {
        try {
            val response = authDataManager.register(register)
            message = response.message
            successful.value = true
        } catch (throwable: Exception) {
            message = throwable.handleNetworkExceptionWithMessage(TAG)
            successful.value = false
        }
    }
}
