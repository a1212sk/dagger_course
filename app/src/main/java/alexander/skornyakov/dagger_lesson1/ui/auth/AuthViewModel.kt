package alexander.skornyakov.dagger_lesson1.ui.auth

import alexander.skornyakov.dagger_lesson1.SessionManager
import alexander.skornyakov.dagger_lesson1.models.User
import alexander.skornyakov.dagger_lesson1.network.auth.AuthApi
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authApi: AuthApi, val sessionManager: SessionManager) :
    ViewModel() {


    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.getUser()

    fun queryUser(userId: Int): LiveData<AuthResource<User>> {

        return LiveDataReactiveStreams.fromPublisher(

            authApi.getUser(userId)
                .onErrorReturn {
                    User(-1)
                }
                .map {
                    if (it.id == -1) {
                        AuthResource.error("Could not authenticate", null)
                    }
                    AuthResource.authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun authenticateWithId(id: Int) {
       sessionManager.authWithId(queryUser(id))
    }

    init {
        if (authApi == null) Log.d("MESSAGE", "IS NULL")
        else Log.d("MESSAGE", "IS NOT NULL")
    }

}
