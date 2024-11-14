package com.example.healthyrecipesapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // încarcă layout-ul

        val searchButton = findViewById<ImageView>(R.id.searchButton)
        searchButton.setOnClickListener {
            val searchQuery = findViewById<EditText>(R.id.searchEditText).text.toString()
            if (searchQuery.isNotEmpty()) {
                val intent = Intent(this, RecipeDetailsActivity::class.java)
                intent.putExtra("query", searchQuery)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Introduceți un text pentru căutare", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}