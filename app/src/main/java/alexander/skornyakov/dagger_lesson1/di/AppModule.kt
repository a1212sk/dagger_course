package alexander.skornyakov.dagger_lesson1.di

import alexander.skornyakov.dagger_lesson1.BaseApplication
import alexander.skornyakov.dagger_lesson1.R
import alexander.skornyakov.dagger_lesson1.util.Constants
import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Module
    companion object {

        @Singleton
        @Provides
        @JvmStatic
        fun provideRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideRequestOptions(): RequestOptions{
            return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background)
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions)
                :RequestManager{
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)
        }

        @Singleton
        @Provides
        @JvmStatic
        fun provideAppDrawable(application: Application):Drawable?{
            return ContextCompat.getDrawable(application, R.drawable.logo)
        }

    }
}