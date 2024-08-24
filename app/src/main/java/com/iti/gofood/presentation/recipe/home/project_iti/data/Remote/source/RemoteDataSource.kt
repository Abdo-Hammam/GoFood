package com.example.project_iti.data.Remote.source

import com.example.project_iti.data.Remote.Retrofit.Meal

interface RemoteDataSource {

    suspend fun getMeals(letter:String): List<Meal>?

    suspend fun searchMeals(query: String): List<Meal>?
}