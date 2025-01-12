package com.example.healthyrecipesapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class RecipeDetailsActivity : AppCompatActivity() {
    private val imageResourceMap = mapOf(
        "meniu_image" to R.drawable.meniu_image,
        "blueberry_muffins" to R.drawable.blueberry_muffins,
        "lipie_cu_ton" to R.drawable.lipie_cu_ton,
        "briose_ou_ciuperci" to R.drawable.briose_ou_ciuperci, // Adaugă alte imagini aici
        "mouse_cu_tofu" to R.drawable.mouse_cu_tofu,
        "biscuiti_cu_banana" to R.drawable.biscuiti_cu_banana,
        "wrap_avocado" to R.drawable.wrap_avocado,
        "pizza" to R.drawable.pizza,
        "pui" to R.drawable.pui,
        "conopida" to R.drawable.conopida,
        "brocoli" to R.drawable.brocoli,
        "cartof_dulce" to R.drawable.cartof_dulce,
        "orez" to R.drawable.orez,
        "vita" to R.drawable.vita,
        "default_image" to R.drawable.meniu_image
    )

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
        // Obține ID-ul imaginii din mapare sau folosește imaginea implicită
        val imageResource = imageResourceMap[imageResId] ?: R.drawable.meniu_image
        imageView.setImageResource(imageResource)


        // Setăm datele în layout
        findViewById<TextView>(R.id.recipeTitle).text = title
        findViewById<TextView>(R.id.recipeTime).text = getString(R.string.recipe_time, time)
        findViewById<TextView>(R.id.recipeServings).text = getString(R.string.recipe_servings, servings)
        findViewById<TextView>(R.id.recipeInstructions).text = instructions
        findViewById<TextView>(R.id.recipeCalories).text = getString(R.string.recipe_calories, calories)
        findViewById<TextView>(R.id.recipeProtein).text = getString(R.string.recipe_protein, protein)
        findViewById<TextView>(R.id.recipeCarbs).text = getString(R.string.recipe_carbs, carbs)
        findViewById<TextView>(R.id.recipeFats).text = getString(R.string.recipe_fats, fats)

        // Dinamic pentru ingrediente
        val ingredientsContainer = findViewById<LinearLayout>(R.id.recipeIngredientsContainer)

        // Transformă lista de ingrediente într-un array și adaugă fiecare ingredient în layout
        val ingredientsList = ingredients.split(",").map { it.trim() }
        for (ingredient in ingredientsList) {
            val textView = TextView(this).apply {
                text = getString(R.string.ingredient_item, ingredient) // Adaugă o liniuță înaintea fiecărui ingredient
                textSize = 14f
                setPadding(0, 8, 0, 8) // Adaugă un spațiu între ingrediente
            }
            ingredientsContainer.addView(textView)
        }
    }
}
