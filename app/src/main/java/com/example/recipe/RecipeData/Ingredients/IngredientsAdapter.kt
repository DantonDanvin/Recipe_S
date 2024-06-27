package com.example.recipe.RecipeData.Ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.recipe.R
import com.example.recipe.RecipeData.RecipeIngredient

class IngredientsAdapter(private var ingredients: List<RecipeIngredient>) : RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName: TextView = itemView.findViewById(R.id.ingredientname)
        val ingredientImage: ImageView = itemView.findViewById(R.id.reImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_template, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.ingredientName.text = ingredient.name

//        val imageLoader = ImageLoader.Builder(holder.ingredientImage.context)
//            .components { add(SvgDecoder.Factory()) }
//            .build()
//        val request = ImageRequest.Builder(holder.ingredientImage.context)
//            .crossfade(true)
//            .crossfade(500)
//            .data(ingredient.image)
//            .target(holder.ingredientImage)
//            .build()
//        imageLoader.enqueue(request)
    }

    override fun getItemCount(): Int = ingredients.size

    fun updateIngredients(newIngredients: List<RecipeIngredient>) {
        ingredients = newIngredients
        notifyDataSetChanged()
    }
}
