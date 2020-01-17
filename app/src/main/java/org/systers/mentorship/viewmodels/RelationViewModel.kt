package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] component used for the Sign Up Activity
 */
class RelationViewModel : BaseViewModel() {

    override val TAG = this::class.java.simpleName

    private val relationDataManager: RelationDataManager = RelationDataManager()

    val successfulCancel: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var mentorshipRelation: Relationship

    /**
     * Fetches current relation details
     */
    fun getCurrentRelationDetails() =
        observe(relationDataManager.getCurrentRelationship(), { mentorshipRelation = it })

    /**
     * Cancels a mentorship relation
     */
    fun cancelMentorshipRelation(relationId: Int) =
        observe(
            relationDataManager.cancelRelationship(relationId),
            success = { successfulCancel.value = true },
            failure = { successfulCancel.value = false },
            changeDefaultStatus = false)
}
