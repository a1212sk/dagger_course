package alexander.skornyakov.dagger_lesson1.ui.main.profile

import alexander.skornyakov.dagger_lesson1.R
import alexander.skornyakov.dagger_lesson1.models.User
import alexander.skornyakov.dagger_lesson1.ui.auth.AuthResource
import alexander.skornyakov.dagger_lesson1.viewmodels.ViewModelProviderFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment:DaggerFragment() {

    private lateinit var viewModel: ProfileViewModel
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    lateinit var email: TextView
    lateinit var username: TextView
    lateinit var website: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Toast.makeText(activity,"Profile Fragment",Toast.LENGTH_LONG).show()
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        website = view.findViewById(R.id.website)

        viewModel = ViewModelProviders.of(this,providerFactory).get(ProfileViewModel::class.java)

        subscribeObservers()
    }

    fun subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Log.d("ProfileFragment",it.data.toString())
                when(it.status){
                    AuthResource.AuthStatus.AUTHENTICATED->{
                        setUserDetails(it.data)
                    }
                    AuthResource.AuthStatus.ERROR->{
                        setErrorDetails(it.message)
                    }
                    else -> {}
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "Error"
        website.text = "Error"
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }
}