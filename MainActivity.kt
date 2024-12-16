package com.example.healthyrecipesapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchButton = findViewById<ImageView>(R.id.searchButton)

        // Colectează toate CheckBox-urile
        val checkBoxBreakfast = findViewById<CheckBox>(R.id.checkBox_breakfast)
        val checkBoxLunch = findViewById<CheckBox>(R.id.checkBox_lunch)
        val checkBoxDinner = findViewById<CheckBox>(R.id.checkBox_diner)
        val checkBoxDessert = findViewById<CheckBox>(R.id.checkBox_destert)
        val checkBoxSnacks = findViewById<CheckBox>(R.id.checkBox_snacks)

        val checkBoxSugarFree = findViewById<CheckBox>(R.id.checkBox_sugar_free)
        val checkBoxLowCarb = findViewById<CheckBox>(R.id.checkBox_low_carb)
        val checkBoxHighProtein = findViewById<CheckBox>(R.id.checkBox_high_protein)

        val checkBoxGlutenFree = findViewById<CheckBox>(R.id.checkBox_gluten_free)
        val checkBoxLactoseFree = findViewById<CheckBox>(R.id.checkBox_lactose_free)
        val checkBoxEggFree = findViewById<CheckBox>(R.id.checkBox_egg_free)

        val checkBoxChicken = findViewById<CheckBox>(R.id.checkBox_chicken)
        val checkBoxTon = findViewById<CheckBox>(R.id.checkBox_ton)
        val checkBoxBeef = findViewById<CheckBox>(R.id.checkBox_beef)
        val checkBoxEgg = findViewById<CheckBox>(R.id.checkBox_egg)
        val checkBoxTofu = findViewById<CheckBox>(R.id.checkBox_tofu)
        val checkBoxRice = findViewById<CheckBox>(R.id.checkBox_rice)
        val checkBoxOvaz = findViewById<CheckBox>(R.id.checkBox_ovaz)
        val checkBoxBrocoli = findViewById<CheckBox>(R.id.checkBox_brocoli)
        val checkBoxAvocado = findViewById<CheckBox>(R.id.checkBox_avocado)
        val checkBoxCartofDulce = findViewById<CheckBox>(R.id.checkBox_cartof_dulce)

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString()
            val filters = mutableMapOf<String, Boolean>()
            // Adaugă toate filtrele în map
            filters["breakfast"] = checkBoxBreakfast.isChecked
            filters["lunch"] = checkBoxLunch.isChecked
            filters["dinner"] = checkBoxDinner.isChecked
            filters["dessert"] = checkBoxDessert.isChecked
            filters["snacks"] = checkBoxSnacks.isChecked

            filters["sugar_free"] = checkBoxSugarFree.isChecked
            filters["low_carb"] = checkBoxLowCarb.isChecked
            filters["high_protein"] = checkBoxHighProtein.isChecked

            filters["gluten_free"] = checkBoxGlutenFree.isChecked
            filters["lactose_free"] = checkBoxLactoseFree.isChecked
            filters["egg_free"] = checkBoxEggFree.isChecked

            filters["chicken"] = checkBoxChicken.isChecked
            filters["ton"] = checkBoxTon.isChecked
            filters["beef"] = checkBoxBeef.isChecked
            filters["egg"] = checkBoxEgg.isChecked
            filters["tofu"] = checkBoxTofu.isChecked
            filters["rice"] = checkBoxRice.isChecked
            filters["ovaz"] = checkBoxOvaz.isChecked
            filters["brocoli"] = checkBoxBrocoli.isChecked
            filters["avocado"] = checkBoxAvocado.isChecked
            filters["cartof_dulce"] = checkBoxCartofDulce.isChecked

            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("searchQuery", searchQuery)
            intent.putExtra("filters", HashMap(filters))
            startActivity(intent)
        }

       // populateDatabase()
    }

    private fun populateDatabase() {
        val db = AppDatabase.getDatabase(this)
        val recipeDao = db.recipeDao()

        CoroutineScope(Dispatchers.IO).launch {
          // recipeDao.deleteAllRecipes() // Șterge toate rețetele existente pentru a evita duplicarea

            recipeDao.insertRecipe(
                Recipe(
                    title = "Blueberry Muffins",
                    time = "10 min",
                    calories = 250,
                    servings = 2,
                    imageResId = "meniu_image",
                    tags = "dessert,low_carb",
                    ingredients = "blueberries,flour,sugar"
                )
            )

            recipeDao.insertRecipe(
                Recipe(
                    title = "Chicken Salad",
                    time = "15 min",
                    calories = 150,
                    servings = 1,
                    imageResId = "meniu_image",
                    tags = "lunch,low_carb",
                    ingredients = "chicken,lettuce,olive oil"
                )
            )

            recipeDao.insertRecipe(
                Recipe(
                    title = "Banana Bread",
                    time = "50 min",
                    calories = 350,
                    servings = 4,
                    imageResId = "meniu_image",
                    tags = "breakfast,sweet",
                    ingredients = "banana,flour,sugar,butter"
                )
            )
        }
    }
}
