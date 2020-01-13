package alexander.skornyakov.dagger_lesson1.ui.main

import alexander.skornyakov.dagger_lesson1.BaseActivity
import alexander.skornyakov.dagger_lesson1.R
import alexander.skornyakov.dagger_lesson1.ui.main.posts.PostsFragment
import alexander.skornyakov.dagger_lesson1.ui.main.profile.ProfileFragment
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        testFragment()
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

    private fun testFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,PostsFragment())
            .commit()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.nav_profile->{

            }
            R.id.nav_posts->{

            }
        }
        p0.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}