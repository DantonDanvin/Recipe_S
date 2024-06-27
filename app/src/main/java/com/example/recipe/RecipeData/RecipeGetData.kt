package com.example.recipe.RecipeData


data class RecipeIngredient(
    val name: String,
    val image: String
)

data class RecipeDetailResponse(
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val summary: String,
    val servings: Int,
    val pricePerServing: Double,
    val extendedIngredients: List<RecipeIngredient>
)


