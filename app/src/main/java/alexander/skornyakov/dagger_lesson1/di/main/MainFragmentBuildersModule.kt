package alexander.skornyakov.dagger_lesson1.di.main

import alexander.skornyakov.dagger_lesson1.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment():ProfileFragment

}