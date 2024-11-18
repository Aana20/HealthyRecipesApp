package com.example.healthyrecipesapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val title = intent.getStringExtra("title") ?: "N/A"
        val time = intent.getStringExtra("time") ?: "N/A"
        val calories = intent.getIntExtra("calories", 0)
        val servings = intent.getIntExtra("servings", 0)

        findViewById<TextView>(R.id.recipeTitle).text = title
        findViewById<TextView>(R.id.recipeTime).text = time
        findViewById<TextView>(R.id.recipeCalories).text = "$calories kcal"
        findViewById<TextView>(R.id.recipeServings).text = "$servings porții"
    }
}
