package com.example.healthyrecipesapp

data class Recipe(
    val title: String,
    val time: String,
    val calories: Int,
    val servings: Int,
    val imageResId: String, // Drawable name for the image
    val tags: List<String>, // Tags like "breakfast", "lunch"
    val ingredients: List<String> // Ingredients
)
