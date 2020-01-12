package alexander.skornyakov.dagger_lesson1.ui.main.profile

import alexander.skornyakov.dagger_lesson1.SessionManager
import alexander.skornyakov.dagger_lesson1.models.User
import alexander.skornyakov.dagger_lesson1.ui.auth.AuthResource
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val sessionManager: SessionManager) : ViewModel(){

    init {
        Log.d("ProfileViewModel","ProfileViewModel is ready")
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>>{
        return sessionManager.getUser()
    }

}