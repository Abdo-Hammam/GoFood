package com.example.project_iti.data.Remote.Retrofit
data class MealResponse(
    val meals: List<Meal>
)

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strTags: String?,
    val strYoutube: String?
)


