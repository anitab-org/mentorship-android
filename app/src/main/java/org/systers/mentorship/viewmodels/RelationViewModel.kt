package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class RelationViewModel : ViewModel() {

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
        relationDataManager.getCurrentRelationship()
                .process { relationship, throwable ->
                    when (throwable) {
                        null -> {
                            when (relationship) {
                                null -> {
                                    successfulGet.postValue(false)
                                }
                                else -> {
                                    successfulGet.postValue(true)
                                    mentorshipRelation = relationship
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successfulGet.postValue(false)
                        }
                    }
                }
    }

    /**
     * Cancels a mentorship relation
     */
    @SuppressLint("CheckResult")
    fun cancelMentorshipRelation(relationId: Int) {
        relationDataManager.cancelRelationship(relationId)
                .process { customResponse, throwable ->
                    when (throwable) {
                        null -> {
                            when (customResponse) {
                                null -> {
                                    successfulCancel.postValue(false)
                                }
                                else -> {
                                    successfulCancel.postValue(true)
                                    message = customResponse.message
                                }
                            }
                        }
                        else -> {
                            message = customResponse!!.message
                            successfulCancel.postValue(false)
                        }
                    }
                }
    }
}

