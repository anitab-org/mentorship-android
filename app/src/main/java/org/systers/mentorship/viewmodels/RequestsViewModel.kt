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
        relationDataManager.getAllRelationsAndRequests()
                .process { list, throwable ->
                    when(throwable){
                        null ->{
                            when(list){
                                null ->{
                                    successful.postValue(false)
                                }
                                else ->{
                                    allRequestsList = list
                                    successful.postValue(true)
                                }
                            }
                        }
                        else ->{
                            message = throwable.localizedMessage
                            successful.postValue(false)
                        }
                    }
                }
    }
}
