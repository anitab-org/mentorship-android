package org.systers.mentorship.remote.customAdapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        return returnType?.let {
            return try {
                // get enclosing type
                val enclosingType = (it as ParameterizedType)

                // ensure enclosing type is 'CustomObservable'
                if (enclosingType.rawType != CustomObservable::class.java)
                    null
                else {
                    val type = enclosingType.actualTypeArguments[0]
                    CustomCallAdapter<Any>(type)
                }
            } catch (ex: ClassCastException) {
                null
            }
        }
    }

    companion object {
        @JvmStatic
        fun create() = CustomCallAdapterFactory()
    }

}
