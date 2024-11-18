package com.example.healthyrecipesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private val recipes = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        // Configurare RecyclerView
        recyclerView = findViewById(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecipeAdapter(recipes) { recipe ->
            // Navigare la detalii (implementare viitoare)
        }
        recyclerView.adapter = adapter

        // Populare rețete
        populateRecipes()
    }

    private fun populateRecipes() {
        val allRecipes = listOf(
            Recipe("Blueberry Muffins", "10 min", 250, 2, R.drawable.meniu_image),
            Recipe("Chocolate Chip Muffins", "15 min", 300, 4, R.drawable.meniu_image),
            Recipe("Banana Muffins", "12 min", 220, 3, R.drawable.meniu_image)
        )
        recipes.clear()
        recipes.addAll(allRecipes)
        adapter.notifyDataSetChanged()
    }
}
