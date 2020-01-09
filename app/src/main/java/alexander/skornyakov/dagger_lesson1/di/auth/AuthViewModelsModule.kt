package alexander.skornyakov.dagger_lesson1.di.auth

import alexander.skornyakov.dagger_lesson1.di.ViewModelKey
import alexander.skornyakov.dagger_lesson1.ui.auth.AuthViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel):ViewModel

}