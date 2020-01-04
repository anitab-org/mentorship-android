package org.systers.mentorship.remote

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.systers.mentorship.MentorshipApplication
import org.systers.mentorship.remote.services.AuthService
import org.systers.mentorship.remote.services.RelationService
import org.systers.mentorship.remote.services.TaskService
import org.systers.mentorship.remote.services.UserService
import org.systers.mentorship.utils.PreferenceManager
import org.systers.mentorship.view.activities.LoginActivity
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A class that represents API Manager. It encapsulates three services: authentication, relation and user.
 */
class ApiManager {

    val authService: AuthService
    val relationService: RelationService
    val userService: UserService
    val taskService: TaskService
    val preferenceManager: PreferenceManager = PreferenceManager()

    companion object {
        private var apiManager: ApiManager? = null

        val instance: ApiManager
            get() {
                if (apiManager == null) {
                    apiManager = ApiManager()
                }
                return apiManager as ApiManager
            }
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(CustomInterceptor())
                .authenticator(object: Authenticator {
                    override fun authenticate(route: Route, response: Response): Request {
                        if (response.code() == 401){
                            //logout if sessions expires and server returns 401
                            val context = MentorshipApplication.getContext()
                            val intent = Intent(context, LoginActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            //for SnackBar message
                            intent.putExtra("toastTokenExpired",1)
                            preferenceManager.clear()
                            startActivity(context,intent,null)
                        }
                        return response.request().newBuilder().build()
                    }
                })
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl.apiBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        authService = retrofit.create(AuthService::class.java)
        relationService = retrofit.create(RelationService::class.java)
        userService = retrofit.create(UserService::class.java)
        taskService = retrofit.create(TaskService::class.java)
    }
}