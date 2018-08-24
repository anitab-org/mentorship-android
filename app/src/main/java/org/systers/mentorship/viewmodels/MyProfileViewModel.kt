package org.systers.mentorship.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.responses.CustomResponse
import org.systers.mentorship.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeoutException
import org.systers.mentorship.remote.datamanager.UserDataManager
import org.systers.mentorship.remote.requests.UpdateUserRequest
import org.systers.mentorship.remote.responses.UserResponse

/**
 * This class represents the [ViewModel] used for My Profile Screen
 */
class MyProfileViewModel : ObservableViewModel() {

    var TAG = MyProfileViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    val getProfileSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val updateProfileSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var getProfileResponse: UserResponse
    lateinit var message: String

    val isEditMode = ObservableBoolean(false)

    var name = ""
    var username = ""
    var email = ""
    var occupation = ""
    var organization = ""
    var bio = ""
    var slackUsername = ""
    var location = ""
    var skills = ""
    var interests = ""
    var availableToMentor = false
    var needMentorship = false

    /**
     * Fetches the current user full profile with data changed by the user
     */
    fun getProfile() {
        userDataManager.getUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<UserResponse>() {
                    override fun onNext(userResponse: UserResponse) {
                        message = MentorshipApplication.getContext()
                                .getString(R.string.fetch_user_profile)
                        getProfileSuccessful.value = true
                        getProfileResponse = userResponse
                        populateView(getProfileResponse)
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out)
                            }
                            is HttpException -> {
                                message = CommonUtils.getErrorResponse(throwable).message.toString()
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        getProfileSuccessful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * Updates the current user profile with data changed by the user
     */
    fun updateProfile() {
        val userRequest = createUpdateUserRequestData()
        userDataManager.updateUser(userRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CustomResponse>() {
                    override fun onNext(customResponse: CustomResponse) {
                        message = customResponse.message ?: MentorshipApplication.getContext()
                                .getString(R.string.update_successful)
                        updateUserResponse(getProfileResponse, userRequest)
                        updateProfileSuccessful.value = true
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_request_timed_out)
                            }
                            is HttpException -> {
                                message = CommonUtils.getErrorResponse(throwable).message.toString()
                            }
                            else -> {
                                message = MentorshipApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        updateProfileSuccessful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }

    /**
     * Populates view with the current user's full profile data
     * @param userResponse contains all data from the current logged in user
     */
    fun populateView(userResponse: UserResponse) {

        name = userResponse.name
        username = userResponse.username
        email = userResponse.email
        occupation = userResponse.occupation
        organization = userResponse.organization
        bio = userResponse.bio
        slackUsername = userResponse.slackUsername
        location = userResponse.location
        skills = userResponse.skills
        interests = userResponse.interests
        availableToMentor = userResponse.isAvailableToMentor
        needMentorship = userResponse.needsMentoring

        notifyChange()
    }

    private fun createUpdateUserRequestData(): UpdateUserRequest {

        val updateUserRequest = UpdateUserRequest()
        updateUserRequest.name = getChangedValue(name, getProfileResponse.name)
        updateUserRequest.username = getChangedValue(username, getProfileResponse.username)
        updateUserRequest.occupation = getChangedValue(occupation, getProfileResponse.occupation)
        updateUserRequest.organization = getChangedValue(organization, getProfileResponse.organization)
        updateUserRequest.bio = getChangedValue(bio, getProfileResponse.bio)
        updateUserRequest.slackUsername = getChangedValue(slackUsername, getProfileResponse.slackUsername)
        updateUserRequest.location = getChangedValue(location, getProfileResponse.location)
        updateUserRequest.skills = getChangedValue(skills, getProfileResponse.skills)
        updateUserRequest.interests = getChangedValue(interests, getProfileResponse.interests)
        updateUserRequest.isAvailableToMentor = getChangedValue(availableToMentor, getProfileResponse.isAvailableToMentor)
        updateUserRequest.needsMentoring = getChangedValue(needMentorship, getProfileResponse.needsMentoring)

        return updateUserRequest
    }

    /**
     * Updates the user's profile response, with the data changed by the current user
     * @param userResponse contains the original fetched user profile data
     * @param updateUserRequest contains the latest changed attributes by the user
     */
    fun updateUserResponse(userResponse: UserResponse, updateUserRequest: UpdateUserRequest) {
        updateUserRequest.name?.let { userResponse.name = it }
        updateUserRequest.username?.let { userResponse.username = it }
        updateUserRequest.occupation?.let { userResponse.occupation = it }
        updateUserRequest.organization?.let { userResponse.organization = it }
        updateUserRequest.bio?.let { userResponse.bio = it }
        updateUserRequest.slackUsername?.let { userResponse.slackUsername = it }
        updateUserRequest.location?.let { userResponse.location = it }
        updateUserRequest.skills?.let { userResponse.skills = it }
        updateUserRequest.interests?.let { userResponse.interests = it }
        updateUserRequest.isAvailableToMentor?.let { userResponse.isAvailableToMentor = it }
        updateUserRequest.needsMentoring?.let { userResponse.needsMentoring = it }
    }

    /**
     * Enables and disables edition mode, affecting the view edit state and
     * the action bar menu item icon
     * @param item menu item responsible for edition switch mode
     */
    fun toggleEditionMode(item: MenuItem) {
        populateView(getProfileResponse)

        val newMode = !isEditMode.get()
        isEditMode.set(newMode)
        val iconRsc = if (newMode) R.drawable.ic_close_white_24dp else R.drawable.ic_edit_white_24dp
        item.icon = ContextCompat.getDrawable(MentorshipApplication.instance, iconRsc)
    }

    private fun getChangedValue(currentValue: String?, originalValue: String?): String? {
        return if (currentValue != originalValue) currentValue else null
    }

    private fun getChangedValue(currentValue: Boolean?, originalValue: Boolean?): Boolean? {
        return if (currentValue != originalValue) currentValue else null
    }
}
