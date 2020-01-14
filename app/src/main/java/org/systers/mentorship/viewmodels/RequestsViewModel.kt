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

    var TAG = RequestsViewModel::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var allRequestsList: List<Relationship>

    /**
     * Fetches list of all Mentorship relations and requests
     */
    @SuppressLint("CheckResult")
    fun getAllMentorshipRelations() {
        relationDataManager.getAllRelationsAndRequests().process { relationsList, throwable ->
            if (relationsList != null) {
                successful.postValue(true)
                allRequestsList = relationsList
            } else if (throwable != null) {
                successful.postValue(false)
                message = throwable.message.toString()
            }
        }
    }
}
