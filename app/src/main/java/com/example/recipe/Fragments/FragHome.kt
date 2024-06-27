package com.example.recipe.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.AllRecipe.RecipesVAdapter
import com.example.recipe.RandomRecipe.Recipe
import com.example.recipe.RandomRecipe.RecipesHAdapter
import com.example.recipe.RandomRecipe.RetrofitClient
import com.example.recipe.RecipeDetail
import com.example.recipe.Search_Recipe
import com.example.recipe.databinding.FragmentFragHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragHome : Fragment(), RecipesVAdapter.OnItemClickListener {

    private lateinit var binding: FragmentFragHomeBinding
    private lateinit var popularRecipes: RecyclerView
    private lateinit var allRecipes: RecyclerView
    private lateinit var recipeshAdapter: RecipesHAdapter
    private lateinit var recipesvAdapter: RecipesVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFragHomeBinding.inflate(inflater, container, false)

        popularRecipes = binding.popularRecipes
        popularRecipes.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        allRecipes = binding.allRecipes
        allRecipes.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.searchBar.setOnClickListener {
            val intent = Intent(context, Search_Recipe::class.java)
            startActivity(intent)
        }

        // Random Recipe.
        try {
            randomRecipe()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

        // all Recipe.
        try {
            allRecipe()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }


    // call api
    private fun randomRecipe() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    RetrofitClient.instance.getRandomRecipes("fdeae4280b5541e9bd5502deb305b188", 10)
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    withContext(Dispatchers.Main) {
                        recipeResponse?.recipes?.let { recipes ->
                            recipeshAdapter = RecipesHAdapter(recipes)
                            popularRecipes.adapter = recipeshAdapter
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // Handle unsuccessful response
                        Toast.makeText(context, "Failed to fetch recipes", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Handle exception
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // call api
    private fun allRecipe() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response =
                    RetrofitClient.instance.getRandomRecipes("fdeae4280b5541e9bd5502deb305b188", 20)
                if (response.isSuccessful) {
                    val recipeResponse = response.body()
                    withContext(Dispatchers.Main) {
                        recipeResponse?.recipes?.let { recipes ->
                            recipesvAdapter = RecipesVAdapter(recipes, this@FragHome)
                            allRecipes.adapter = recipesvAdapter
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        // Handle unsuccessful response
                        Toast.makeText(context, "Failed to fetch recipes", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    // Handle exception
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onItemClick(recipe: Recipe) {
//        Toast.makeText(context, "Clicked: ${recipe.title}", Toast.LENGTH_SHORT).show()
        val intent = Intent(activity, RecipeDetail::class.java).apply {
            putExtra("id", recipe.id.toString())
        }
        startActivity(intent)
    }


}