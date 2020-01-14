package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class RelationViewModel : ViewModel() {

    var TAG = RelationViewModel::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    val successfulGet: MutableLiveData<Boolean> = MutableLiveData()
    val successfulCancel: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var mentorshipRelation: Relationship
    lateinit var message: String

    /**
     * Fetches current relation details
     */
    @SuppressLint("CheckResult")
    fun getCurrentRelationDetails() {
        relationDataManager.getCurrentRelationship().process { relationship, throwable ->
            if (relationship != null) {
                successfulGet.postValue(true)
                mentorshipRelation = relationship
            } else if (throwable != null) {
                successfulGet.postValue(false)
                message = throwable.message.toString()
            }
        }
    }

    /**
     * Cancels a mentorship relation
     */
    @SuppressLint("CheckResult")
    fun cancelMentorshipRelation(relationId: Int) {
        relationDataManager.cancelRelationship(relationId).process { customResponse, throwable ->
            if (customResponse != null) {
                successfulCancel.postValue(true)
                message = customResponse.message
            } else if (throwable != null) {
                successfulCancel.postValue(false)
                message = throwable.message.toString()
            }
        }
    }
}
