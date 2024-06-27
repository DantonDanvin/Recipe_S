package com.example.recipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.recipe.databinding.ActivityMainBinding

    private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        // Global map accessible throughout the application
        var favItem: MutableMap<String, Any> = mutableMapOf()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        binding.login.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}