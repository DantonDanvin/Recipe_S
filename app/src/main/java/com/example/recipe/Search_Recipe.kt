package com.example.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.recipe.databinding.ActivityHomeBinding
import com.example.recipe.databinding.ActivitySearchRecipeBinding

private lateinit var binding: ActivitySearchRecipeBinding

class Search_Recipe : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)



    }
}