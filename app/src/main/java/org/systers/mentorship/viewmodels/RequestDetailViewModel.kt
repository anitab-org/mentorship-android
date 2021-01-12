package org.systers.mentorship.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
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
    /* @SuppressLint("CheckResult")
     fun acceptRequest(requestId: Int) {
         relationDataManager.acceptRelationship(requestId)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(object : DisposableObserver<CustomResponse>() {
                     override fun onNext(customResponse: CustomResponse) {
                         message = customResponse.message
                         successful.value = true
                     }

                     override fun onError(throwable: Throwable) {
                         when (throwable) {
                             is IOException -> {
                                 message = MentorshipApplication.getContext()
                                         .getString(R.string.error_please_check_internet)
                             }
                             is TimeoutException -> {
                                 message = MentorshipApplication.getContext()
                                         .getString(R.string.error_request_timed_out)
                             }
                             is HttpException -> {
                                 message = CommonUtils.getErrorResponse(throwable).message
                             }
                             else -> {
                                 message = MentorshipApplication.getContext()
                                         .getString(R.string.error_something_went_wrong)
                                 Log.e(tag, throwable.localizedMessage)
                             }
                         }
                         successful.value = false
                     }

                     override fun onComplete() {
                     }
                 })
     }*/

    /**
     * Rejects a mentorship request
     * @param requestId id of the mentorship request
     */
    /*  @SuppressLint("CheckResult")
      fun rejectRequest(requestId: Int) {
          relationDataManager.rejectRelationship(requestId)
                  .subscribeOn(Schedulers.newThread())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeWith(object : DisposableObserver<CustomResponse>() {
                      override fun onNext(customResponse: CustomResponse) {
                          message = customResponse.message
                          successful.value = true
                      }

                      override fun onError(throwable: Throwable) {
                          when (throwable) {
                              is IOException -> {
                                  message = MentorshipApplication.getContext()
                                          .getString(R.string.error_please_check_internet)
                              }
                              is TimeoutException -> {
                                  message = MentorshipApplication.getContext()
                                          .getString(R.string.error_request_timed_out)
                              }
                              is HttpException -> {
                                  message = CommonUtils.getErrorResponse(throwable).message
                              }
                              else -> {
                                  message = MentorshipApplication.getContext()
                                          .getString(R.string.error_something_went_wrong)
                                  Log.e(tag, throwable.localizedMessage)
                              }
                          }
                          successful.value = false
                      }

                      override fun onComplete() {
                      }
                  })
      }*/

    /**
     * Deletes a mentorship request
     * @param requestId id of the mentorship request
     */
    /* @SuppressLint("CheckResult")
     fun deleteRequest(requestId: Int) {
         relationDataManager.deleteRelationship(requestId)
                 .subscribeOn(Schedulers.newThread())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribeWith(object : DisposableObserver<CustomResponse>() {
                     override fun onNext(customResponse: CustomResponse) {
                         message = customResponse.message
                         successful.value = true
                     }

                     override fun onError(throwable: Throwable) {
                         when (throwable) {
                             is IOException -> {
                                 message = MentorshipApplication.getContext()
                                         .getString(R.string.error_please_check_internet)
                             }
                             is TimeoutException -> {
                                 message = MentorshipApplication.getContext()
                                         .getString(R.string.error_request_timed_out)
                             }
                             is HttpException -> {
                                 message = CommonUtils.getErrorResponse(throwable).message
                             }
                             else -> {
                                 message = MentorshipApplication.getContext()
                                         .getString(R.string.error_something_went_wrong)
                                 Log.e(tag, throwable.localizedMessage)
                             }
                         }
                         successful.value = false
                     }

                     override fun onComplete() {
                     }
                 })
     }*/

    fun acceptRequest(requestId: Int) {
        viewModelScope.launch {
            try {
                message = relationDataManager.acceptRelationship(requestId).message
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    fun rejectRequest(requestId: Int) {
        viewModelScope.launch {
            try {
                message = relationDataManager.rejectRelationship(requestId).message
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }

    fun deleteRequest(requestId: Int) {
        viewModelScope.launch {
            try {
                message = relationDataManager.deleteRelationship(requestId).message
                successful.postValue(true)
            } catch (throwable: Throwable) {
                message = CommonUtils.getErrorMessage(throwable, tag)
                successful.postValue(false)
            }
        }
    }
}

