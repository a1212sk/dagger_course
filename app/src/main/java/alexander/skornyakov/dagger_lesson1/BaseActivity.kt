package alexander.skornyakov.dagger_lesson1

import alexander.skornyakov.dagger_lesson1.ui.auth.AuthActivity
import alexander.skornyakov.dagger_lesson1.ui.auth.AuthResource
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity(){

    @Inject lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        sessionManager.getUser().observe(this, Observer {
            if(it!=null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING->{
                    }
                    AuthResource.AuthStatus.AUTHENTICATED->{

                    }
                    AuthResource.AuthStatus.ERROR->{
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED->{
                        navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen(){
        val intent: Intent = Intent(this,AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}