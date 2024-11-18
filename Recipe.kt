package com.example.healthyrecipesapp

data class Recipe(
    val title: String,
    val time: String,
    val calories: Int,
    val servings: Int,
    val imageResId: Int // ID-ul resursei drawable
)
