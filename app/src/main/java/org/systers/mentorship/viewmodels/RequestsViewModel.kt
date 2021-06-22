package org.systers.mentorship.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.db.AppDb
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.utils.CommonUtils

/**
 * This class represents the [ViewModel] used for Requests Screen
 */
class RequestsViewModel : ViewModel() {

    var tag = RequestsViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    val pendingSuccessful: MutableLiveData<Boolean> = MutableLiveData()

    var message: String? = null

    var allRequestsList: List<Relationship> = ArrayList()
    var pastRequestsList: List<Relationship> = ArrayList()
    var pendingAllRequestsList: List<Relationship> = ArrayList()
    /**
     * Fetches list of all Mentorship relations and requests
     */
    fun getAllMentorshipRelations(context: Context) {
        viewModelScope.launch {
            try {
                allRequestsList = relationDataManager.getAllRelationsAndRequests().sortedByDescending { it.creationDate }
                allRequestsList.forEach {
                    insertRelationships(context, it)
                }
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    /**
     * Fetches list of all pending Mentorship relations and requests
     */
    fun getAllPendingMentorshipRelations(context: Context) {
        viewModelScope.launch {
            try {
                pendingAllRequestsList = relationDataManager.getAllPendingRelationsAndRequests().sortedByDescending { it.creationDate }
                pendingAllRequestsList.forEach {
                    it.pending = 1
                    insertRelationships(context, it)
                }
                pendingSuccessful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                pendingSuccessful.postValue(false)
            }
        }
    }

    /* past mentorship relations working:
       1. RelationshipService.kt makes API call to mentorship_relations/past
       2. getPastRelationships() in RelationDataManager.kt reads this as Observable<List<Relationship>>
       3. getPastMentorshipRelations() in RequestsViewModel.kt subscribes to the data and manages exception handling
       4. getPastMentorshipRelations() called in RequestsFragment.kt. It displays this data in fragment_requests.xml
        */
    fun getPastMentorshipRelations(context: Context) {
        viewModelScope.launch {
            try {
                pastRequestsList = relationDataManager.getPastRelationships().sortedByDescending { it.creationDate }
                pastRequestsList.forEach {
                    insertRelationships(context, it)
                }
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    /**
     * Inserts Relationships in the database
     */

    fun insertRelationships(context: Context, relationship: Relationship) = viewModelScope.launch {
        val relationshipDao = AppDb(context).getRelationshipDao()
        relationshipDao.insertRelationship(relationship)
    }

    /**
     * Fetches all relationships from the database
     */
    fun getAllRelationshipsFromDb(context: Context) = AppDb(context).getRelationshipDao().getAllRelationships()

    /**
     * Fetches all pending relations from the database
     */
    fun getAllPendingRelationshipsFromDb(context: Context) = AppDb(context).getRelationshipDao().getPendingRelationships()
}
