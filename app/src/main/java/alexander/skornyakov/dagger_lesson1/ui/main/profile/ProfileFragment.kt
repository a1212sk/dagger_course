package alexander.skornyakov.dagger_lesson1.ui.main.profile

import alexander.skornyakov.dagger_lesson1.R
import alexander.skornyakov.dagger_lesson1.viewmodels.ViewModelProviderFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment:DaggerFragment() {

    private lateinit var viewModel: ProfileViewModel
    @Inject lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Toast.makeText(activity,"Profile Fragment",Toast.LENGTH_LONG).show()
        viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel::class.java)
    }
}