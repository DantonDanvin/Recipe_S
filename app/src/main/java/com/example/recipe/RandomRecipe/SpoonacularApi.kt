package com.example.recipe.RandomRecipe

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpoonacularApi {
    @GET("random")
    suspend fun getRandomRecipes(
        @Header("x-api-key") apiKey: String,
        @Query("number") number: Int
    ): Response<RecipeResponse>
}
