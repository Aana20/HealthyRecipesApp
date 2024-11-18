package com.example.healthyrecipesapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private val recipes = mutableListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        recyclerView = findViewById(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RecipeAdapter(recipes) { recipe ->
            // Navigare la detalii (implementare viitoare)
            Log.d("RecipeClick", "Ai selectat rețeta: ${recipe.title}")
        }
        recyclerView.adapter = adapter

        // Preia textul introdus și filtrele selectate
        val searchQuery = intent.getStringExtra("searchQuery") ?: ""
        val filters = intent.getSerializableExtra("filters") as? Map<String, Boolean> ?: emptyMap()

        loadRecipesFromJson(searchQuery, filters)
    }

    private fun loadRecipesFromJson(searchQuery: String, filters: Map<String, Boolean>) {
        val json = resources.openRawResource(R.raw.recipes).bufferedReader().use { it.readText() }
        val gson = Gson()
        val type = object : TypeToken<List<Recipe>>() {}.type
        val allRecipes: List<Recipe> = gson.fromJson(json, type)

        // Loghează filtrele pentru verificare
        filters.forEach { (key, isChecked) ->
            Log.d("Filters", "$key: $isChecked")
        }

        // Filtrare pe baza textului din bara de căutare și tag-uri
        val filteredRecipes = allRecipes.filter { recipe ->
            val matchesQuery = recipe.title.contains(searchQuery, ignoreCase = true)
            val matchesFilters = filters.all { (key, isChecked) ->
                if (isChecked) recipe.tags.contains(key) else true
            }
            matchesQuery && matchesFilters
        }

        // Actualizează lista cu rețetele filtrate
        recipes.clear()
        recipes.addAll(filteredRecipes)
        adapter.notifyDataSetChanged()
    }
}
