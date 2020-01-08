package org.systers.mentorship.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager
import org.systers.mentorship.vo.Resource

/**
 * This class represents the [ViewModel] used for Requests Screen
 */
class RequestsViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    private val relationDataManager = RelationDataManager()

    /**
     * Fetches list of all Mentorship relations and requests
     */
    fun getAllMentorshipRelations(): LiveData<Resource<List<Relationship>>> =
        relationDataManager.getAllRelationsAndRequests()

}
