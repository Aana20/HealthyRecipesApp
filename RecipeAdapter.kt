package com.example.healthyrecipesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val recipes: List<Recipe>,
    private val onRecipeClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView = view.findViewById(R.id.recipeImage)
        val recipeTitle: TextView = view.findViewById(R.id.recipeTitle)
        val recipeTime: TextView = view.findViewById(R.id.recipeTime)
        val recipeCalories: TextView = view.findViewById(R.id.recipeCalories)
        val recipeServings: TextView = view.findViewById(R.id.recipeServings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeTitle.text = recipe.title
        holder.recipeTime.text = recipe.time
        holder.recipeCalories.text = "${recipe.calories} kcal"
        holder.recipeServings.text = "${recipe.servings} porții"

        // Set image from drawable using resource name
        val imageResId = holder.itemView.context.resources.getIdentifier(
            recipe.imageResId, "drawable", holder.itemView.context.packageName
        )
        holder.recipeImage.setImageResource(imageResId)

        holder.itemView.setOnClickListener { onRecipeClick(recipe) }
    }

    override fun getItemCount(): Int = recipes.size
}
