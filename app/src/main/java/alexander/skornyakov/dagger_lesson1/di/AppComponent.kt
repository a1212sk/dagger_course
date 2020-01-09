package alexander.skornyakov.dagger_lesson1.di

import alexander.skornyakov.dagger_lesson1.BaseApplication
import alexander.skornyakov.dagger_lesson1.SessionManager
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    ViewModelFactoryModule::class
])
interface AppComponent: AndroidInjector<BaseApplication> {

    fun sessionManager():SessionManager

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun app(app: Application):Builder

        fun build(): AppComponent
    }
}