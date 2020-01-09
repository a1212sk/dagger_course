package alexander.skornyakov.dagger_lesson1.di

import alexander.skornyakov.dagger_lesson1.viewmodels.ViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory):ViewModelProvider.Factory

}