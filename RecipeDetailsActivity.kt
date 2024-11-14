package com.example.healthyrecipesapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class RecipeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        // Afișează un mesaj temporar pe ecran pentru a verifica navigarea
        val messageTextView: TextView = findViewById(R.id.temporaryText)
        messageTextView.text = "Ecranul de detalii al rețetei"


        // Setează titlul în Action Bar
        supportActionBar?.title = "Detalii Rețetă"

        // Activează butonul „Up”
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Gestionează acțiunea de click pe butonul „Up”
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()  // Închide activitatea curentă
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
