package com.example.healthyrecipesapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("""
        SELECT * FROM recipes 
        WHERE (:searchQuery IS NULL OR title LIKE '%' || :searchQuery || '%' OR tags LIKE '%' || :searchQuery || '%')
        AND (:filterBreakfast = 0 OR tags LIKE '%breakfast%') 
        AND (:filterLunch = 0 OR tags LIKE '%lunch%') 
        AND (:filterDinner = 0 OR tags LIKE '%dinner%') 
        AND (:filterDessert = 0 OR tags LIKE '%dessert%') 
        AND (:filterSnacks = 0 OR tags LIKE '%snacks%') 
        AND (:filterSugarFree = 0 OR tags LIKE '%sugar_free%') 
        AND (:filterLowCarb = 0 OR tags LIKE '%low_carb%') 
        AND (:filterHighProtein = 0 OR tags LIKE '%high_protein%') 
        AND (:filterGlutenFree = 0 OR tags LIKE '%gluten_free%') 
        AND (:filterLactoseFree = 0 OR tags LIKE '%lactose_free%') 
        AND (:filterEggFree = 0 OR tags LIKE '%egg_free%') 
        AND (:filterChicken = 0 OR tags LIKE '%chicken%') 
        AND (:filterTon = 0 OR tags LIKE '%ton%') 
        AND (:filterBeef = 0 OR tags LIKE '%beef%') 
        AND (:filterEgg = 0 OR tags LIKE '%egg%') 
        AND (:filterTofu = 0 OR tags LIKE '%tofu%') 
        AND (:filterRice = 0 OR tags LIKE '%rice%') 
        AND (:filterOvaz = 0 OR tags LIKE '%ovaz%') 
        AND (:filterBrocoli = 0 OR tags LIKE '%brocoli%') 
        AND (:filterAvocado = 0 OR tags LIKE '%avocado%') 
        AND (:filterCartofDulce = 0 OR tags LIKE '%cartof_dulce%')
    """)
    fun getFilteredRecipes(
        searchQuery: String?,
        filterBreakfast: Int,
        filterLunch: Int,
        filterDinner: Int,
        filterDessert: Int,
        filterSnacks: Int,
        filterSugarFree: Int,
        filterLowCarb: Int,
        filterHighProtein: Int,
        filterGlutenFree: Int,
        filterLactoseFree: Int,
        filterEggFree: Int,
        filterChicken: Int,
        filterTon: Int,
        filterBeef: Int,
        filterEgg: Int,
        filterTofu: Int,
        filterRice: Int,
        filterOvaz: Int,
        filterBrocoli: Int,
        filterAvocado: Int,
        filterCartofDulce: Int
    ): List<Recipe>


    @Insert
    fun insertRecipe(recipe: Recipe)

    @Query("DELETE FROM recipes")
    fun deleteAllRecipes()
}
