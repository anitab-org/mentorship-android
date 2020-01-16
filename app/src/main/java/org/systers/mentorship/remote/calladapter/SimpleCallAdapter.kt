package org.systers.mentorship.remote.calladapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class SimpleCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Any> {

    override fun adapt(call: Call<T>) = Simple(call)

    override fun responseType() = responseType

}