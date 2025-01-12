package com.example.healthyrecipesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val time: String,
    val calories: Int,
    val servings: Int,
    val imageResId: String,
    val tags: String,
    val ingredients: String,
    val instructions: String,
    val protein: Int,
    val carbs: Int,
    val fats: Int
)
