package com.example.recipe.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.AllRecipe.RecipesVAdapter
import com.example.recipe.MainActivity.Companion.favItem
import com.example.recipe.RandomRecipe.Recipe
import com.example.recipe.databinding.FragmentFragFavouriteBinding

class FragFavourite : Fragment() {

    private lateinit var binding: FragmentFragFavouriteBinding
    private lateinit var allRecipes: RecyclerView
    private lateinit var recipesvAdapter: RecipesVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFragFavouriteBinding.inflate(inflater, container, false)

        allRecipes = binding.allRecipes
        allRecipes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        println("favItem $favItem")

        // Extract data from favItem map
        val recipeList = mutableListOf<Recipe>()
        for (entry in favItem.entries) {
            val recipeData = entry.value as Map<String, Any>
            val recipe = Recipe(
                id = entry.key.toInt(),
                title = recipeData["title"] as String,
                readyInMinutes = recipeData["readyInMinutes"] as Int,
                image = recipeData["image"] as String
            )
            recipeList.add(recipe)
        }

        // Initialize and set up RecyclerView adapter
        recipesvAdapter = RecipesVAdapter(recipeList, object : RecipesVAdapter.OnItemClickListener {
            override fun onItemClick(recipe: Recipe) {
                // Handle item click if needed
                // For example, navigate to recipe detail page
                // You can access recipe properties like recipe.title, recipe.image, etc.
            }
        })
        allRecipes.adapter = recipesvAdapter

        return binding.root
    }
}
