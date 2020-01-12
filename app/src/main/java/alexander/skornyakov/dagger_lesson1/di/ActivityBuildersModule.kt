package alexander.skornyakov.dagger_lesson1.di

import alexander.skornyakov.dagger_lesson1.di.auth.AuthModule
import alexander.skornyakov.dagger_lesson1.di.auth.AuthViewModelsModule
import alexander.skornyakov.dagger_lesson1.di.main.MainFragmentBuildersModule
import alexander.skornyakov.dagger_lesson1.di.main.MainModule
import alexander.skornyakov.dagger_lesson1.di.main.MainViewModelsModule
import alexander.skornyakov.dagger_lesson1.ui.auth.AuthActivity
import alexander.skornyakov.dagger_lesson1.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class,
            AuthModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity

}