package com.example.recipe

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.recipe.MainActivity.Companion.favItem
import com.example.recipe.RecipeData.Ingredients.IngredientsAdapter
import com.example.recipe.RecipeData.RecipeDetailResponse
import com.example.recipe.RecipeData.RetrofitDataClient
import com.example.recipe.databinding.ActivityRecipeDetailBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetail : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailBinding
    private lateinit var ingredientsAdapter: IngredientsAdapter

    private var recipeId = ""
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        recipeId = intent.getStringExtra("id").toString()

        // Initialize RecyclerView
        binding.ingredientsrecycler.apply {
            layoutManager = LinearLayoutManager(this@RecipeDetail, LinearLayoutManager.HORIZONTAL, false)
            ingredientsAdapter = IngredientsAdapter(emptyList())
            adapter = ingredientsAdapter
        }

        // RecipeDetail.
        try {
            recipeDetails()
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    // Call API
    private fun recipeDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitDataClient.instance.getRecipeDetails(
                    recipeId.toInt(),
                    "fdeae4280b5541e9bd5502deb305b188"
                )
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    withContext(Dispatchers.Main) {
                        recipeResponse?.let { displayRecipeDetails(it) }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // Handle unsuccessful response
                        Toast.makeText(this@RecipeDetail, "Failed to fetch recipes", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Handle exception
                    Toast.makeText(this@RecipeDetail, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Display Recipe Details
    private fun displayRecipeDetails(recipeDetail: RecipeDetailResponse) {
        val imageLoader = ImageLoader.Builder(binding.recIcon.context)
            .components { add(SvgDecoder.Factory()) }
            .build()
        val request = ImageRequest.Builder(binding.recIcon.context)
            .crossfade(true)
            .crossfade(500)
            .data(recipeDetail.image)
            .target(binding.recIcon)
            .build()
        imageLoader.enqueue(request)

        binding.title.text = recipeDetail.title
        binding.instructions.text = recipeDetail.summary
        binding.quickSummary.text = recipeDetail.summary
        binding.readyInMinutes.text = recipeDetail.readyInMinutes.toString()
        binding.readyInMinutes.append(" min")
        binding.servings.text = recipeDetail.servings.toString()
        binding.pricePerServing.text = recipeDetail.pricePerServing.toString()

        ingredientsAdapter.updateIngredients(recipeDetail.extendedIngredients)

        binding.like.setOnClickListener {
            if (isLiked) {
                // Change the src to the 'unliked' drawable
                binding.like.setImageResource(R.drawable.addtofavourit)
            }else {
                // Change the src to the 'liked' drawable
                binding.like.setImageResource(R.drawable.added)

                favItem[recipeId] = mapOf(
                    "title" to recipeDetail.title,
                    "image" to recipeDetail.image,
                    "readyInMinutes" to recipeDetail.readyInMinutes
                )
            }
            isLiked = !isLiked
        }
    }
}