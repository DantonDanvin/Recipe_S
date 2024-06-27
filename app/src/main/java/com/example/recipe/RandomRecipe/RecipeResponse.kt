package com.example.recipe.RandomRecipe

data class RecipeResponse(
    val recipes: List<Recipe>
)

data class Recipe(
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val id: Int
)

