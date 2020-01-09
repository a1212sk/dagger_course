package alexander.skornyakov.dagger_lesson1.di.auth

import alexander.skornyakov.dagger_lesson1.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule{

    @Module
    companion object{

        @Provides
        @JvmStatic
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }

    }

}