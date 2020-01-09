package alexander.skornyakov.dagger_lesson1

import alexander.skornyakov.dagger_lesson1.models.User
import alexander.skornyakov.dagger_lesson1.ui.auth.AuthResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(){
    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authWithId(source: LiveData<AuthResource<User>>){
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logOut(){
        cachedUser.value = AuthResource.logout()
    }

    public fun getUser()=cachedUser

}
