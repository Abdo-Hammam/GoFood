package com.example.project_iti.repo

import com.example.project_iti.data.Remote.Retrofit.Meal
import com.example.project_iti.data.Remote.source.RemoteDataSource

class Repo(private val remoteDataSource: RemoteDataSource) {
    suspend fun getAllMeals(letter:String): List<Meal>? {
        return remoteDataSource.getMeals(letter)
    }

    suspend fun searchMeals(query: String): List<Meal>? {
        return remoteDataSource.searchMeals(query)
    }
}
