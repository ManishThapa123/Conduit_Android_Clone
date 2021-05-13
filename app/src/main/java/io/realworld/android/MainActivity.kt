package io.realworld.android

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import io.realworld.api.models.entities.User
import www.sanju.motiontoast.MotionToast

class MainActivity : AppCompatActivity() {

    companion object{
        const val PREF_APP_AUTH = "pref_auth"
        const val PREFS_KEY_TOKEN = "token"
    }

    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var appBarConfiguration: AppBarConfiguration
    var navView : NavigationView? = null
    var navController: NavController? = null
    var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
//        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)

         drawerLayout = findViewById(R.id.drawer_layout)
         navView = findViewById(R.id.nav_view)

        sharedPreferences = getSharedPreferences(PREF_APP_AUTH, Context.MODE_PRIVATE)


        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_feed,
            R.id.nav_my_feed,
        R.id.nav_auth), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)

        sharedPreferences.getString(PREFS_KEY_TOKEN, null)?.let {
            authViewModel.getCurrentUser(it)
        }
        authViewModel.user.observe({lifecycle}){
            updateMenu(it)

            it?.token?.let {t->String
                sharedPreferences.edit{
                    putString(PREFS_KEY_TOKEN,t)
                }
                MotionToast.darkColorToast(this,
                    "Hurray success",
                    "Logged in as ${it.username}",
                    MotionToast.TOAST_SUCCESS,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.helvetica_regular))

            } ?: run{
                sharedPreferences.edit{
                    remove(PREFS_KEY_TOKEN)
                }
                MotionToast.darkColorToast(this,
                    "Adiós",
                    "Come back soon!",
                    MotionToast.TOAST_INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(this,R.font.helvetica_regular))
            }


            navController.navigateUp()
        }
    }

    private fun updateMenu(user: User?){

        when(user) {
            is User ->{
                navView?.menu?.clear()
                navView?.inflateMenu(R.menu.menu_main_user)
            }
            else ->{
                navView?.menu?.clear()
                navView?.inflateMenu(R.menu.menu_main_guest)

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_logout ->{
                authViewModel.logout()
                return true
            }

        }
        return super.onOptionsItemSelected(item)

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}