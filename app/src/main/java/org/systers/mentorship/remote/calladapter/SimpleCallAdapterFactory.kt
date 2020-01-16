package org.systers.mentorship.remote.calladapter

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class SimpleCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit) =
            returnType.let {
                try {
                    // ensure enclosing type is 'Simple'
                    if ((it as ParameterizedType).rawType != Simple::class.java)
                        null
                    else {
                        SimpleCallAdapter<Any>(it.actualTypeArguments[0])
                    }
                } catch (ex: ClassCastException) {
                    null
                }
            }

}