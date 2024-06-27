package com.example.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.recipe.Fragments.FragFavourite
import com.example.recipe.Fragments.FragHome
import com.example.recipe.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var binding: ActivityHomeBinding
    private lateinit var bottomNavView: BottomNavigationView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)


        // Bottom navigation listener.
        bottomNavView = findViewById(R.id.bottom_nav_view)
        bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_Home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FragHome()).commit()
                    true
                }
                R.id.navigation_Favourite -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FragFavourite()).commit()
                    true
                }
                else -> false
            }
        }
        bottomNavView.setOnItemReselectedListener { item ->
            // Do nothing when the item is reselected
        }

        // set Market fragment by default.
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FragHome()).commit()
        bottomNavView.selectedItemId = R.id.navigation_Home





    }



}