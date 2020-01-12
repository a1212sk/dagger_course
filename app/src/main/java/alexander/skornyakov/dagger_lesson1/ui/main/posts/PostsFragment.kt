package alexander.skornyakov.dagger_lesson1.ui.main.posts

import alexander.skornyakov.dagger_lesson1.R
import alexander.skornyakov.dagger_lesson1.viewmodels.ViewModelProviderFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment(){

    private lateinit var viewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject lateinit var viewModelProviderFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_posts,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(PostsViewModel::class.java)
        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Log.d("PostsFragment",it.data.toString())
            }
        })
    }
}