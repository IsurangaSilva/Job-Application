package com.example.madd

import com.example.madd.databinding.ActivityMainBinding
import android.icu.text.CaseMap.Title
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> replaceFragment2(HomeFragment(), it.title.toString())
                R.id.jobs -> replaceFragment2(JobFragment(), it.title.toString())
                R.id.post -> replaceFragment2(Add_postFragment(), it.title.toString())
                R.id.feedback -> replaceFragment2(FeedbackFragment(), it.title.toString())

                else -> {


                }

            }

            true

        }


        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)



        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {

            it.isChecked = true
            when (it.itemId) {

                R.id.nav_profile -> replaceFragment2(ProfileFragment(), it.title.toString())
                R.id.nav_editProfile -> replaceFragment2(Edit_ProfileFragment(), it.title.toString())
                R.id.nav_addImage -> replaceFragment2(Edit_ImageFragment(), it.title.toString())
                R.id.nav_remove -> replaceFragment2(RemoveFragment(), it.title.toString())
                R.id.nav_logout -> replaceFragment2(LogoutFragment(), it.title.toString())
            }
            true
        }



    }



    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }

    private fun replaceFragment2(fragment : Fragment,title: String){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}