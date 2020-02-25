package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] used for Requests Screen
 */
class RequestsViewModel : ViewModel() {

    var tag = RequestsViewModel::class.java.simpleName!!

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    val pendingSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var pendingAllRequestsList: List<Relationship>

    var message: String? = null
    var allRequestsList: List<Relationship>? = null
    var pastRequestsList: List<Relationship>? = null

    /**
     * Fetches list of all Mentorship relations and requests
     */
    @SuppressLint("CheckResult")
    fun getAllMentorshipRelations() {
        relationDataManager.getAllRelationsAndRequests()
                .process { list, throwable ->
                    when (throwable) {
                        null -> {
                            when (list) {
                                null -> {
                                    successful.postValue(false)
                                }
                                else -> {
                                    allRequestsList = list
                                    successful.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successful.postValue(false)
                        }
                    }
                }
    }

    /**
     * Fetches list of all pending Mentorship relations and requests
     */
    @SuppressLint("CheckResult")
    fun getAllPendingMentorshipRelations() {
        relationDataManager.getAllPendingRelationsAndRequests()
                .process { list, throwable ->
                    when (throwable) {
                        null -> {
                            when (list) {
                                null -> {
                                    pendingSuccessful.postValue(false)
                                }
                                else -> {
                                    pendingAllRequestsList = list
                                    pendingSuccessful.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            pendingSuccessful.postValue(false)
                        }
                    }
                }
    }


    /* past mentorship relations working:
       1. RelationshipService.kt makes API call to mentorship_relations/past
       2. getPastRelationships() in RelationDataManager.kt reads this as Observable<List<Relationship>>
       3. getPastMentorshipRelations() in RequestsViewModel.kt subscribes to the data and manages exception handling
       4. getPastMentorshipRelations() called in RequestsFragment.kt. It displays this data in fragment_requests.xml
        */
    @SuppressLint("CheckResult")
    fun getPastMentorshipRelations() {
        relationDataManager.getPastRelationships()
                .process { list, throwable ->
                    when (throwable) {
                        null -> {
                            when (list) {
                                null -> {
                                    successful.postValue(false)
                                }
                                else -> {
                                    pastRequestsList = list
                                    successful.postValue(true)
                                }
                            }
                        }
                        else -> {
                            message = throwable.localizedMessage
                            successful.postValue(false)
                        }
                    }
                }
    }
}



