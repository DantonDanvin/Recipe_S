package com.example.recipe.RandomRecipe

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

class RecipesHAdapter(private val recipes: List<Recipe>) : RecyclerView.Adapter<RecipesHAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.recipe_title)
        val readyInMinutes: TextView = itemView.findViewById(R.id.recipe_ready_time)
        var image: ImageView = itemView.findViewById(R.id.reImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        var view: View=LayoutInflater.from(parent.context).inflate(R.layout.popular_recipes_template, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.title
        holder.readyInMinutes.text = "Ready in ${recipe.readyInMinutes} min"
//        Glide.with(holder.image.context).load(recipe.image).into(holder.image)
        // it'll load both PNG and SVG.
        val imageLoader = ImageLoader.Builder(holder.image.context)
            .components { add(SvgDecoder.Factory()) }
            .build()
        val request = ImageRequest.Builder(holder.image.context)
            .crossfade(true)
            .crossfade(500)
            .data(recipe.image)
            .target(holder.image)
            .build()
        imageLoader.enqueue(request)
    }

    override fun getItemCount(): Int = recipes.size
}
