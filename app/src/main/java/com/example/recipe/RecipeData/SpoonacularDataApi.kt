package com.example.recipe.RecipeData

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularDataApi {

    @GET("{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") id: Int,
        @Header("x-api-key") apiKey: String
    ): Response<RecipeDetailResponse>
}
