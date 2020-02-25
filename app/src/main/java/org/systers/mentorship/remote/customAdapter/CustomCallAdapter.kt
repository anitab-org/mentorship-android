package org.systers.mentorship.remote.customAdapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**custom adapter for retrofit
 */
class CustomCallAdapter<T>(private val responseType: Type): CallAdapter<T, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Any = CustomObservable(call)
}
