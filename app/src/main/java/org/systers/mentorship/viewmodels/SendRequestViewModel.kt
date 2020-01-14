package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.R
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.requests.RelationshipRequest
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the [ViewModel] component used for the Send Request Activity
 */
class SendRequestViewModel : BaseViewModel() {

    override var TAG = SendRequestViewModel::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    /**
     * Call send a mentorship request service
     * @param relationshipRequest object containing mentorship request details
     */
    @SuppressLint("CheckResult")
    fun sendRequest(@NonNull relationshipRequest: RelationshipRequest) {
        relationDataManager.sendRequest(relationshipRequest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    message = (it as CustomResponse).message
                    if (message.isEmpty())
                        message = MentorshipApplication.getContext()
                                .getString(R.string.registration_successful)

                })
    }
}
