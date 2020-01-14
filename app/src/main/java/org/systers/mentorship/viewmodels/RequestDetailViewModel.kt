package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the [ViewModel] used for Request Detail Screen
 */
class RequestDetailViewModel : BaseViewModel() {

    override var TAG = RequestDetailViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    /**
     * Accepts a mentorship request
     * @param requestId id of the mentorship request
     */
    @SuppressLint("CheckResult")
    fun acceptRequest(requestId: Int) {
        relationDataManager.acceptRelationship(requestId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    message = (it as CustomResponse).message
                })
    }

    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    @SuppressLint("CheckResult")
    fun rejectRequest(requestId: Int) {
        relationDataManager.rejectRelationship(requestId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    message = (it as CustomResponse).message
                })
    }

    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    @SuppressLint("CheckResult")
    fun deleteRequest(requestId: Int) {
        relationDataManager.deleteRelationship(requestId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer {
                    message = (it as CustomResponse).message
                })
    }
}
