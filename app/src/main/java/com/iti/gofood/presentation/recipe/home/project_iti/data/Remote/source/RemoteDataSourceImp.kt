package com.example.project_iti.data.Remote.source

import com.example.project_iti.data.Remote.Retrofit.Meal
import com.example.project_iti.data.Remote.Retrofit.MealResponse
import com.example.project_iti.data.Remote.Retrofit.MealService


class RemoteDataSourceImpl(private val service: MealService) : RemoteDataSource {

    override suspend fun getMeals(letter:String): List<Meal>? {
        return try {
            val response = service.getMeals(letter)
            response.meals
        } catch (e: Exception) {
            e.message
            null
        }
    }

    override suspend fun searchMeals(query: String): List<Meal>? {
        return try {
            val response = service.searchMeals(query)
            response.meals
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getRecipeById(id: String): MealResponse {
        return service.getRecipeById(id)
    }
}


