package com.fulora.online

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.fulora.preference.getUserEmail
import com.fulora.preference.getUserName
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textview.MaterialTextView

class OnlineActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var textNavUser: MaterialTextView
    private lateinit var textNavEmail: MaterialTextView

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_online)
        setSupportActionBar(findViewById(R.id.toolbar))

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val host: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = host?.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_home, R.id.menu_about, R.id.menu_access_account, R.id.menu_exit
            ), drawerLayout
        )
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Insert User name and email in my NavView.
        val  headerLayout = navView.inflateHeaderView(R.layout.nav_header_main)
        textNavUser = headerLayout.findViewById(R.id.text_nav_name)
        textNavEmail = headerLayout.findViewById(R.id.text_nav_email)

        textNavUser.text = getUserName(this)
        textNavEmail.text = getUserEmail(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val host: NavHostFragment? = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        val navController = host!!.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                true
            }
            R.id.menu_about -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(R.string.title_about)
                    .setMessage(R.string.about_text)
                    .show()

                true
            }
            R.id.menu_access_account -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.accountFragment)
                true
            }
            R.id.menu_exit -> {
                MaterialAlertDialogBuilder(this)
                    .setTitle(resources.getString(R.string.quit))
                    .setMessage(resources.getString(R.string.want_to_quit))
                    .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                        // Respond to negative button press
                        dialog.cancel()
                    }
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, _ ->
                        // Respond to positive button press
                        dialog.dismiss()
//                        cleanUser(this)
//                        startActivity(Intent(this, OfflineActivity::class.java))
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}