package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.remote.responses.CustomResponse

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class RelationViewModel : BaseViewModel() {

    override var TAG: String = RelationViewModel::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    val successfulCancel: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var mentorshipRelation: Relationship

    /**
     * Fetches current relation details
     */
    @SuppressLint("CheckResult")
    fun getCurrentRelationDetails() {
        relationDataManager.getCurrentRelationship()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer(
                        next = { mentorshipRelation = it as Relationship
                            successfulGet.value = true
                        },
                        error = {successfulGet.value = false}
                ))
    }

    /**
     * Cancels a mentorship relation
     */
    @SuppressLint("CheckResult")
    fun cancelMentorshipRelation(relationId: Int) {
        relationDataManager.cancelRelationship(relationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer(
                        next = {
                            message = (it as CustomResponse).message
                            successfulCancel.value = true
                        },
                        error = {successfulCancel.value = false}
                ))
    }
}
