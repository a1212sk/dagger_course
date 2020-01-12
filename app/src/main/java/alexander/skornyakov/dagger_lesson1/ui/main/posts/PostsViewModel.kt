package alexander.skornyakov.dagger_lesson1.ui.main.posts

import alexander.skornyakov.dagger_lesson1.SessionManager
import alexander.skornyakov.dagger_lesson1.models.Post
import alexander.skornyakov.dagger_lesson1.network.main.MainApi
import alexander.skornyakov.dagger_lesson1.ui.main.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    val sessionManager: SessionManager
    , val mainApi: MainApi
) : ViewModel() {

    lateinit var posts: MediatorLiveData<Resource<List<Post>>>

    fun observePosts():LiveData<Resource<List<Post>>>{
        if(!::posts.isInitialized){
            posts = MediatorLiveData()
            posts.value = Resource.loading(null )

            val source = LiveDataReactiveStreams.fromPublisher(
                mainApi.getPostsFromUser(sessionManager.getUser().value?.data?.id!!)
                    .onErrorReturn {
                        val list = ArrayList<Post>()
                        val post = Post(-1)
                        list.add(post)
                        list
                    }
                    .map {
                        if(it.size>0){
                            if(it[0].id==-1){
                                Resource.error("Something went wrong",null)
                            }
                        }
                        Resource.success(it)
                    }
                    .subscribeOn(Schedulers.io())
            )
            posts.addSource(source){
                posts.value = it
                posts.removeSource(source)
            }
        }
        return posts
    }


}