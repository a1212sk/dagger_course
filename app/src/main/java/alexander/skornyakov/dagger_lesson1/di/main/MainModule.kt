package alexander.skornyakov.dagger_lesson1.di.main

import alexander.skornyakov.dagger_lesson1.network.main.MainApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun getMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }
    }

}