package alexander.skornyakov.dagger_lesson1.network.main

import alexander.skornyakov.dagger_lesson1.models.Post
import androidx.lifecycle.LiveData
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    //posts?userId=1
    @GET("posts")
    fun getPostsFromUser(@Query("userId") userId:Int): Flowable<List<Post>>

}