package org.systers.mentorship.vo


/**
 * A generic class that holds a value with its loading status.
 **/
sealed class Resource<out T> {
    companion object {
        fun <T> success(data: T): SuccessResource<T> {
            return SuccessResource(data)
        }

        fun <T> error(message: String, data: T?): ErrorResource<T> {
            return ErrorResource(message, data)
        }

        fun <T> loading(data: T?): LoadingResource<T> {
            return LoadingResource(data)
        }
    }
}

data class SuccessResource<T>(val data: T) : Resource<T>()

data class ErrorResource<T>(val message: String, val data: T?) : Resource<T>()

data class LoadingResource<T>(val data: T?) : Resource<T>()
