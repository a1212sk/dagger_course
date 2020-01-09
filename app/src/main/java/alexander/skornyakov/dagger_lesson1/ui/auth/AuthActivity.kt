package alexander.skornyakov.dagger_lesson1.ui.auth

import alexander.skornyakov.dagger_lesson1.R
import alexander.skornyakov.dagger_lesson1.ui.main.MainActivity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() , View.OnClickListener{
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.login_button->{
                attemptLogin()
            }
        }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(userId.text.toString())) return;
        viewModel.authenticateWithId(Integer.parseInt(userId.text.toString()))
    }

    @Inject @JvmField var logo: Drawable? = null

    @Inject lateinit var requestManager: RequestManager

    private lateinit var userId : EditText
    private lateinit var progressBar: ProgressBar

    @Inject lateinit var providerFactory: ViewModelProvider.Factory
   lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        userId = findViewById(R.id.user_id_input)
        findViewById<View>(R.id.login_button).setOnClickListener(this)
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel::class.java)
        progressBar = findViewById(R.id.progress_bar)

        setLogo()
        subscribeObservers()

    }

    fun setLogo(){
        requestManager.load(logo).into(findViewById(R.id.login_logo))
    }

    private fun showProgressBar(isVisible:Boolean){
        if(isVisible){
            progressBar.visibility = View.VISIBLE
        }
        else{
            progressBar.visibility = View.GONE
        }
    }

    fun onLoginSuccess(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(i)
        finish()
    }

    fun subscribeObservers(){
        viewModel.observeAuthState().observe(this, Observer {
            if(it!=null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING->{
                        showProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED->{
                        showProgressBar(false)
                        Log.d("MESSAGE",it.data?.email)
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR->{
                        showProgressBar(false)
                        Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED->{
                        showProgressBar(false)
                    }
                }
            }
        })
    }
}
