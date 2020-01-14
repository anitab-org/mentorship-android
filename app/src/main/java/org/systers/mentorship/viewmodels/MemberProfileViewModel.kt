package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.models.User
import org.systers.mentorship.remote.datamanager.UserDataManager

/**
 * This class represents the [ViewModel] component used for the MemberProfileActivity
 */
class MemberProfileViewModel : BaseViewModel() {

    override var TAG = MemberProfileViewModel::class.java.simpleName

    private val userDataManager: UserDataManager = UserDataManager()

    lateinit var userProfile: User

    /**
     * Fetches profile from a user with id [userId] by calling getUser method from UserService
     * @param userId id of a member
     */
    @SuppressLint("CheckResult")
    fun getUserProfile(@NonNull userId: Int) {
        userDataManager.getUser(userId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    userProfile = it as User
                })
    }
}
