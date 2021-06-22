package org.systers.mentorship.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.systers.mentorship.db.AppDb
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.utils.CommonUtils

/**
 * This class represents the [ViewModel] used for Request Detail Screen
 */
class RequestDetailViewModel : ViewModel() {

    var tag = RequestDetailViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String

    /**
     * Accepts a mentorship request
     * @param requestId id of the mentorship request
     */
    fun acceptRequest(context: Context, requestId: Int) {
        viewModelScope.launch {
            try {
                message = relationDataManager.acceptRelationship(requestId).message
                deleteRelationshipFromDb(context, requestId)
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    fun rejectRequest(context: Context, requestId: Int) {
        viewModelScope.launch {
            try {
                message = relationDataManager.rejectRelationship(requestId).message
                deleteRelationshipFromDb(context, requestId)
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    fun deleteRequest(context: Context, requestId: Int) {
        viewModelScope.launch {
            try {
                message = relationDataManager.deleteRelationship(requestId).message
                deleteRelationshipFromDb(context, requestId)
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    /**
     * Deletes relationships from the database
     */

    fun deleteRelationshipFromDb(context: Context, id: Int) = viewModelScope.launch {
        val relationshipDao = AppDb(context).getRelationshipDao()
        relationshipDao.deleteRelatioship(id)
    }
}
