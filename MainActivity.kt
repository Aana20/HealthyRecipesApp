package com.example.healthyrecipesapp

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val searchButton = findViewById<ImageView>(R.id.searchButton)

        val checkBoxBreakfast = findViewById<CheckBox>(R.id.checkBox_breakfast)
        val checkBoxLunch = findViewById<CheckBox>(R.id.checkBox_lunch)
        val checkBoxSugarFree = findViewById<CheckBox>(R.id.checkBox_sugar_free)
        val checkBoxLowCarb = findViewById<CheckBox>(R.id.checkBox_low_carb)
        val checkBoxGlutenFree = findViewById<CheckBox>(R.id.checkBox_gluten_free)
        val checkBoxLactoseFree = findViewById<CheckBox>(R.id.checkBox_lactose_free)
        val checkBoxChicken = findViewById<CheckBox>(R.id.checkBox_chicken)

        searchButton.setOnClickListener {
            val searchQuery = searchEditText.text.toString()
            val filters = mutableMapOf<String, Boolean>()
            filters["Mic dejun"] = checkBoxBreakfast.isChecked
            filters["lunch"] = checkBoxLunch.isChecked
            filters["sugarFree"] = checkBoxSugarFree.isChecked
            filters["Low carb"] = checkBoxLowCarb.isChecked
            filters["glutenFree"] = checkBoxGlutenFree.isChecked
            filters["lactoseFree"] = checkBoxLactoseFree.isChecked
            filters["Pui"] = checkBoxChicken.isChecked // Pentru rețetele cu pui

            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("searchQuery", searchQuery)
            intent.putExtra("filters", HashMap(filters))
            startActivity(intent)
        }
    }
}
