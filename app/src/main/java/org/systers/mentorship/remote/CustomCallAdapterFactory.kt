package org.systers.mentorship.remote

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.ClassCastException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * CustomCallAdapterFactory class to inform Retrofit of our CustomCallAdapter.
 **/
class CustomCallAdapterFactory private constructor(): CallAdapter.Factory(){

    /**
     * This method returns the instance of CustomCallAdapter class with type argument as the response-type.
     * */
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return returnType.let {
            try {
                val enclosingType = (it as ParameterizedType)
                /**
                 * The retrofit instance can contain multiple call adapter factories for different call adapters,
                 * so if the CallAdapter is not of our concern, we return null
                 * */
                if(enclosingType.rawType != Custom::class.java ) null
                else {
                    val type = enclosingType.actualTypeArguments[0]
                    CustomCallAdapter<Any>(type)
                }
            } catch (ex: ClassCastException) {
                null
            }
        }
    }

    /**
     * Creates an instance of CustomCallAdapterFactory
     * */
    companion object {
        @JvmStatic
        fun create() = CustomCallAdapterFactory()
    }
}