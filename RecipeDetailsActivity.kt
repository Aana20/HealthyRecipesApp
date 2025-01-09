package com.example.healthyrecipesapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthyrecipesapp.R

class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        // Preluăm datele din Intent
        val imageResId = intent.getStringExtra("imageResId") ?: "default_image"
        val title = intent.getStringExtra("title") ?: "Titlul rețetei"
        val time = intent.getStringExtra("time") ?: "Timp necunoscut"
        val servings = intent.getIntExtra("servings", 0)
        val instructions = intent.getStringExtra("instructions") ?: "Mod de preparare indisponibil"
        val ingredients = intent.getStringExtra("ingredients") ?: "Ingrediente indisponibile"
        val calories = intent.getIntExtra("calories", 0)
        val protein = intent.getIntExtra("protein", 0)
        val carbs = intent.getIntExtra("carbs", 0)
        val fats = intent.getIntExtra("fats", 0)

        // Setăm imaginea
        val imageView = findViewById<ImageView>(R.id.recipeImage)
        val imageResource = resources.getIdentifier(imageResId, "drawable", packageName)
        imageView.setImageResource(imageResource)

        // Setăm datele în layout
        findViewById<TextView>(R.id.recipeTitle).text = title
        findViewById<TextView>(R.id.recipeTime).text = "Timp de pregătire: $time"
        findViewById<TextView>(R.id.recipeServings).text = "Cantitate rezultată: $servings porții"
        findViewById<TextView>(R.id.recipeInstructions).text = instructions
        findViewById<TextView>(R.id.recipeCalories).text = "Calorii: $calories Kcal"
        findViewById<TextView>(R.id.recipeProtein).text = "Proteine: $protein g"
        findViewById<TextView>(R.id.recipeCarbs).text = "Carbohidrați: $carbs g"
        findViewById<TextView>(R.id.recipeFats).text = "Grăsimi: $fats g"

        // Dinamic pentru ingrediente
        val ingredientsContainer = findViewById<LinearLayout>(R.id.recipeIngredientsContainer)

        // Transformă lista de ingrediente într-un array și adaugă fiecare ingredient în layout
        val ingredientsList = ingredients.split(",").map { it.trim() }
        for (ingredient in ingredientsList) {
            val textView = TextView(this).apply {
                text = "- $ingredient" // Adaugă o liniuță înaintea fiecărui ingredient
                textSize = 14f
                setPadding(0, 8, 0, 8) // Adaugă un spațiu între ingrediente
            }
            ingredientsContainer.addView(textView)
        }
    }
}
