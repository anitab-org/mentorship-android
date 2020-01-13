package org.systers.mentorship.remote

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * AdapterFactory converting [retrofit2.Call<T>] to [org.systers.mentorship.remote.Simple]
 */
class SimpleCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        return try {
            val enclosingType = (returnType as ParameterizedType)

            if (enclosingType.rawType != Simple::class.java) null
            else {
                val type = enclosingType.actualTypeArguments[0]
                SimpleCallAdapter<Any>(type)
            }
        } catch (ex: ClassCastException) {
            null
        }
    }

    companion object {

        @JvmStatic
        fun create() = SimpleCallAdapterFactory()
    }
}
