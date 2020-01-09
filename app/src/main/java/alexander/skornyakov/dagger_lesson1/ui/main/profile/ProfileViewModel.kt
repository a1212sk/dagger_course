package alexander.skornyakov.dagger_lesson1.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel(){

    init {
        Log.d("ProfileViewModel","ProfileViewModel is ready")
    }

}