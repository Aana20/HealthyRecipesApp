package com.example.healthyrecipesapp

import com.example.healthyrecipesapp.Recipe
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val recipe = intent.getSerializableExtra("recipe") as? Recipe

        val recipeImage = findViewById<ImageView>(R.id.recipeImage)
        val recipeTitle = findViewById<TextView>(R.id.recipeTitle)
        val recipeTime = findViewById<TextView>(R.id.recipeTime)
        val recipeServings = findViewById<TextView>(R.id.recipeServings)
        val recipeCalories = findViewById<TextView>(R.id.recipeCalories)

        recipe?.let {
            recipeTitle.text = it.title
            recipeTime.text = "Time: ${it.time}"
            recipeServings.text = "Servings: ${it.servings}"
            recipeCalories.text = "Calories: ${it.calories} kcal"
        }
    }
}
