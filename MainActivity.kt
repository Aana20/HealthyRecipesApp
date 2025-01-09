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

        populateDatabase()
    }

    private fun populateDatabase() {
        val db = AppDatabase.getDatabase(this)
        val recipeDao = db.recipeDao()

        CoroutineScope(Dispatchers.IO).launch {
           recipeDao.deleteAllRecipes() // Șterge toate rețetele existente pentru a evita duplicarea

            recipeDao.insertRecipe(
                Recipe(
                    title = "Blueberry Muffins",
                    time = "10 min",
                    calories = 250,
                    servings = 2,
                    imageResId = "meniu_image",
                    tags = "dessert,low_carb",
                    ingredients = "blueberries,flour,sugar",
                    instructions = "Brioșe cu cacao sănătoase și ușor de preparat din doar 5 ingrediente. Brioșele nu conțin făină, nu conțin gluten și sunt îndulcite cu miere. La prepararea acestora puteți folosi cacao sau carob. Pregătiți-le pentru micul dejun sau ca gustare de dimineață, dar sunt perfecte și ca desert sănătos pentru după-amiază.\n" +
                            "\n" +
                            "Pentru a pregăti aceste brioșe aveți nevoie doar de cacao, ouă, miere, ulei de cocos și bicarbonat de sodiu. Credeți sau nu, cacaoa înlocuiește complet făina din brioșe, și cum folosim până la 4 linguri de cacao, acestea vor avea un gust pregnant de ciocolată. Aceste brioșe fără gluten sunt perfecte nu doar pentru cei care suferă de boala celiacă, dar și pentru persoanele care doresc să evite făina din alte motive. Iar câteodată cu toții ne dorim să încercăm ceva diferit, nu-i așa? Cred că această rețetă este ideală pentru toți începătorii într-ale bucătăriei sănătoase.\n" +
                            "\n" +
                            "Dacă doriți să obțineți mai multe brioșe (ceea ce cred că e cazul), dublați sau triplați cantitatea de ingrediente listate în rețetă. Vă recomand să folosiți forme din silicon pentru a coace brioșele.\n" +
                            "\n" +
                            "Aceste brioșe sănătoase fără făină cu cacao se numără printre deserturile mele preferate, în plus, sunt extrem de ușor de preparat. Trebuie să le încercați cât mai curând, cred că vă vor plăcea mult. Iar pe lângă brioșe, din această rețetă puteți pregăti un flan de cacao sănătos.",
                    protein = 5,
                    carbs = 20,
                    fats = 10
                )
            )
/*
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
            )*/
        }
    }
}
