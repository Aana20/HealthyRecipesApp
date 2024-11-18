package com.example.healthyrecipesapp

import android.content.Intent
import android.os.Bundle
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
            val intent = Intent(this, RecipeDetailsActivity::class.java)
            intent.putExtra("title", recipe.title)
            intent.putExtra("time", recipe.time)
            intent.putExtra("calories", recipe.calories)
            intent.putExtra("servings", recipe.servings)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val searchQuery = intent.getStringExtra("searchQuery") ?: ""
        val filters = intent.getSerializableExtra("filters") as? Map<String, Boolean> ?: emptyMap()

        loadRecipesFromJson(searchQuery, filters)
    }

    private fun loadRecipesFromJson(searchQuery: String, filters: Map<String, Boolean>) {
        val json = resources.openRawResource(R.raw.recipes).bufferedReader().use { it.readText() }
        val gson = Gson()
        val type = object : TypeToken<List<Recipe>>() {}.type
        val allRecipes: List<Recipe> = gson.fromJson(json, type)

        val filteredRecipes = allRecipes.filter { recipe ->
            val matchesQuery = recipe.title.contains(searchQuery, ignoreCase = true)
            val matchesFilters = filters.all { (key, isChecked) ->
                if (isChecked) recipe.tags.contains(key) else true
            }
            matchesQuery && matchesFilters
        }

        recipes.clear()
        recipes.addAll(filteredRecipes)
        adapter.notifyDataSetChanged()
    }
}
