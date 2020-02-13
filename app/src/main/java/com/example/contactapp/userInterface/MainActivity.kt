package com.example.contactapp.userInterface

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityContactBinding
import com.example.contactapp.utils.SettingsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_contact.*
import kotlin.system.exitProcess
val TABLE  = "contacts"
class MainActivity:AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var binding: ActivityContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DataBindingUtil.setContentView<ActivityContactBinding>(this, R.layout.activity_contact)
        navController = this.findNavController(R.id.nav_host_fragment)

        setSupportActionBar(toolbarall)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.tools_menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingpage) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        if (item.itemId == R.id.logout) {
            val sharedPreferences: SharedPreferences =
                getSharedPreferences("loginref", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            finish()
        }
        return true
    }

//    private var navigationChange = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//
//        when (item.itemId) {
//            R.id.home_button -> {
//                createFragmentOne()
//                toolbarall.title = "All Contacts"
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.search_button -> {
//                createFragmentTwo()
//                toolbarall.title = "Search"
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.add_button -> {
//                createFragmentThree()
//                toolbarall.title = "Add Contact"
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.favourite_button -> {
//                createFragmentfour()
//                toolbarall.title = "Settings"
//                return@OnNavigationItemSelectedListener true
//            }
//
//        }
//        false
//
//    }

//    fun addNew(v: View){
//        val intent = Intent(this, MainActivitySaveContact::class.java)
//        startActivity(intent)
//    }
//
//    private fun createFragmentOne()
//    {
//        createFragment(FragmentContact())
//    }
//
//    private fun createFragmentTwo()
//    {
//        createFragment(FrangmentSearch())
//    }
//
//    private fun createFragmentThree()
//    {
//        createFragment(FragmentAddContact())
//    }
//
//    private fun createFragmentfour()
//    {
//        createFragment(FragmentFavourite())
//    }
//
//    private fun createFragment(frag: Fragment) {
//        val fragmentTransct = supportFragmentManager.beginTransaction()
//        fragmentTransct.replace(R.id.nav_host_fragment, frag)
//        fragmentTransct.addToBackStack(null)
//        fragmentTransct.commit()
//    }






}