package com.example.recipe.AllRecipe

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
import com.example.recipe.RandomRecipe.Recipe

class RecipesVAdapter(
    private val recipes: List<Recipe>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecipesVAdapter.RecipeViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(recipe: Recipe)
    }

    class RecipeViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.recipe_title)
        val readyInMinutes: TextView = itemView.findViewById(R.id.recipe_ready_time)
        var image: ImageView = itemView.findViewById(R.id.reImage)

        fun bind(recipe: Recipe) {
            itemView.setOnClickListener {
                listener.onItemClick(recipe)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_recipes_template, parent, false)
        return RecipeViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.title.text = recipe.title
        holder.readyInMinutes.text = "Ready in ${recipe.readyInMinutes} min"
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

        // Set item click listener
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size
}
