package alexander.skornyakov.dagger_lesson1.ui.main

import alexander.skornyakov.dagger_lesson1.BaseActivity
import alexander.skornyakov.dagger_lesson1.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class MainActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.logout -> {
                sessionManager.logOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}