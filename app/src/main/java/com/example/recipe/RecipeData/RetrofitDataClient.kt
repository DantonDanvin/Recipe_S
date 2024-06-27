package com.example.recipe.RecipeData

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDataClient {
    private const val BASE_URL = "https://api.spoonacular.com/recipes/"

    val instance: SpoonacularDataApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SpoonacularDataApi::class.java)
    }
}
