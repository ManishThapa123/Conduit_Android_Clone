package io.realworld.android

import android.os.Bundle
import android.view.Menu
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import io.realworld.api.models.entities.User

class MainActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()
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
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_feed,
            R.id.nav_my_feed,
        R.id.nav_auth), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView?.setupWithNavController(navController)

        authViewModel.user.observe({lifecycle}){
            updateMenu(it)
            navController.navigateUp()
            Toast.makeText(this,"Logged in as ${it?.username}", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateMenu(user: User?){

        when(user) {
            is User ->{
                navView?.menu?.clear()
                navView?.inflateMenu(R.menu.menu_main_user)
            }
            else ->{


            }
        }
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