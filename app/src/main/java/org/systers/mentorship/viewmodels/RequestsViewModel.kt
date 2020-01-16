package org.systers.mentorship.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.systers.mentorship.models.Relationship
import org.systers.mentorship.remote.datamanager.RelationDataManager

/**
 * This class represents the [ViewModel] used for Requests Screen
 */
class RequestsViewModel : BaseViewModel() {

    override var TAG: String = RequestsViewModel::class.java.simpleName
    private val relationDataManager = RelationDataManager()
    lateinit var allRequestsList: List<Relationship>

    /**
     * Fetches list of all Mentorship relations and requests
     */
    @SuppressLint("CheckResult")
    fun getAllMentorshipRelations() {
        relationDataManager.getAllRelationsAndRequests()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer(
                        next = {
                            @Suppress("UNCHECKED_CAST")
                            allRequestsList = it as List<Relationship>
                            successful.value = true
                        },
                        error = {successful.value = false}
                ))
    }
}
