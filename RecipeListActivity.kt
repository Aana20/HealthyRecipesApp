package com.example.healthyrecipesapp

//import RecipeDetailsActivity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            val intent = Intent(this, RecipeDetailsActivity::class.java).apply {
                putExtra("imageResId", recipe.imageResId)
                putExtra("title", recipe.title)
                putExtra("time", recipe.time)
                putExtra("servings", recipe.servings)
                putExtra("instructions", recipe.instructions)
                putExtra("ingredients", recipe.ingredients)
                putExtra("calories", recipe.calories)
                putExtra("protein", recipe.protein) // Transmite valoarea reală
                putExtra("carbs", recipe.carbs)     // Transmite valoarea reală
                putExtra("fats", recipe.fats)       // Transmite valoarea reală
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        val searchQuery = intent.getStringExtra("searchQuery")
        val filters = intent.getSerializableExtra("filters") as? Map<String, Boolean> ?: emptyMap()

        loadRecipes(searchQuery, filters)
    }

    private fun loadRecipes(searchQuery: String?, filters: Map<String, Boolean>) {
        val db = AppDatabase.getDatabase(this)
        val recipeDao = db.recipeDao()

        CoroutineScope(Dispatchers.IO).launch {
            val filteredRecipes = recipeDao.getFilteredRecipes(
                searchQuery = searchQuery,
                filterBreakfast = if (filters["breakfast"] == true) 1 else 0,
                filterLunch = if (filters["lunch"] == true) 1 else 0,
                filterDinner = if (filters["dinner"] == true) 1 else 0,
                filterDessert = if (filters["dessert"] == true) 1 else 0,
                filterSnacks = if (filters["snacks"] == true) 1 else 0,
                filterSugarFree = if (filters["sugar_free"] == true) 1 else 0,
                filterLowCarb = if (filters["low_carb"] == true) 1 else 0,
                filterHighProtein = if (filters["high_protein"] == true) 1 else 0,
                filterGlutenFree = if (filters["gluten_free"] == true) 1 else 0,
                filterLactoseFree = if (filters["lactose_free"] == true) 1 else 0,
                filterEggFree = if (filters["egg_free"] == true) 1 else 0,
                filterChicken = if (filters["chicken"] == true) 1 else 0,
                filterTon = if (filters["ton"] == true) 1 else 0,
                filterBeef = if (filters["beef"] == true) 1 else 0,
                filterEgg = if (filters["egg"] == true) 1 else 0,
                filterTofu = if (filters["tofu"] == true) 1 else 0,
                filterRice = if (filters["rice"] == true) 1 else 0,
                filterOvaz = if (filters["ovaz"] == true) 1 else 0,
                filterBrocoli = if (filters["brocoli"] == true) 1 else 0,
                filterAvocado = if (filters["avocado"] == true) 1 else 0,
                filterCartofDulce = if (filters["cartof_dulce"] == true) 1 else 0
            )

            recipes.clear()
            recipes.addAll(filteredRecipes)
            runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }
}
