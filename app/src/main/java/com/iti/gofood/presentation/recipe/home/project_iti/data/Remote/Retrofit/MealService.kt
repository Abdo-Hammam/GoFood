package com.example.project_iti.data.Remote.Retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealService {

    @GET("search.php")
    suspend fun getMeals(@Query("f") letter: String): MealResponse

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse
    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") id: String): MealResponse
}