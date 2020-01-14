package org.systers.mentorship.remote

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * CustomCallAdapter class to inform Retrofit of our Custom transformer.
 **/
class CustomCallAdapter<T> (private val responseType: Type) : CallAdapter<T, Any> {

    override fun adapt(call: Call<T>): Any = Custom(call)

    override fun responseType(): Type = responseType
}
