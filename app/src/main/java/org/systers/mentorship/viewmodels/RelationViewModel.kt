package org.systers.mentorship.viewmodels

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
    fun getCurrentRelationDetails() {
        relationDataManager.getCurrentRelationship().process { relationship, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successfulGet.postValue(false)
            } else {
                if (relationship != null) {
                    mentorshipRelation = relationship
                    successfulGet.postValue(true)
                } else {
                    successfulGet.postValue(false)
                }
            }
        }
    }

    /**
     * Cancels a mentorship relation
     */
    fun cancelMentorshipRelation(relationId: Int) {
        relationDataManager.cancelRelationship(relationId).process { customResponse, throwable ->
            if (throwable != null) {
                throwable.printStackTrace()
                message = throwable.localizedMessage
                successfulCancel.postValue(false)
            } else {
                if (customResponse != null) {
                    message = customResponse.message
                    successfulCancel.postValue(true)
                } else {
                    successfulCancel.postValue(false)
                }
            }
        }
    }
}
