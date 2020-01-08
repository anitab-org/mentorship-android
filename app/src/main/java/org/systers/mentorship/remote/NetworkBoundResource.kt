package org.systers.mentorship.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import org.systers.mentorship.remote.responses.ApiEmptyResponse
import org.systers.mentorship.remote.responses.ApiErrorResponse
import org.systers.mentorship.remote.responses.ApiResponse
import org.systers.mentorship.remote.responses.ApiSuccessResponse
import org.systers.mentorship.vo.Resource

/**
 * A generic class providing a resource backed by both the local SQLite database and the network.
 **/
abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("LeakingThis")
        val dbSource = loadFromDb()
        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        // Re-attach dbSource as a new source, so it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        result.addSource(apiResponse) { response: ApiResponse<RequestType> ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiSuccessResponse -> {
                    saveCallResult(processResponse(response))
                    // Save call to DB and serve it from there
                    // <DB is a single source of truth>
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))


                    }
                }
                is ApiEmptyResponse -> {
                    // Reload from disk what was before
                    result.addSource(loadFromDb()) { newData ->
                        setValue(Resource.success(newData))

                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(Resource.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    protected abstract fun saveCallResult(item: RequestType)

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDb(): LiveData<ResultType>

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}
