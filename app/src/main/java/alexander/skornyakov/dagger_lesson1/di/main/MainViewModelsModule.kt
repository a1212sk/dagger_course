package alexander.skornyakov.dagger_lesson1.di.main

import alexander.skornyakov.dagger_lesson1.di.ViewModelKey
import alexander.skornyakov.dagger_lesson1.ui.main.posts.PostsFragment
import alexander.skornyakov.dagger_lesson1.ui.main.posts.PostsViewModel
import alexander.skornyakov.dagger_lesson1.ui.main.profile.ProfileViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule{

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel):ViewModel

}