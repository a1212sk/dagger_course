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
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
        init()
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
            android.R.id.home->{
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return true
                }
                else{
                    return false
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    fun isValidNavigation(id:Int):Boolean{
        return id!=Navigation.findNavController(this,R.id.fragment).currentDestination?.id
    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.nav_profile->{
                val navOptions = NavOptions.Builder().setPopUpTo(R.id.main,true).build()
                Navigation.findNavController(this,R.id.fragment).navigate(R.id.profileScreen
                ,null,navOptions)
            }
            R.id.nav_posts->{
                if(isValidNavigation(R.id.postsScreen))
                Navigation.findNavController(this,R.id.fragment).navigate(R.id.postsScreen)
            }
        }
        p0.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun init(){
        val navController: NavController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
        NavigationUI.setupWithNavController(navigationView,navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(R.id.fragment),drawerLayout)
    }
}