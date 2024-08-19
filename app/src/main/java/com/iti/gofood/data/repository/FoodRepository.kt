package com.iti.gofood.data.repository

import com.iti.gofood.data.remotesource.retrofit.ApiService
import com.iti.gofood.data.remotesource.retrofit.models.response
import retrofit2.Call

interface FoodRepository {
    suspend fun getaFood(): Call<response>
}

class NetworkFoodRepository(
    private val foodApiService: ApiService
) : FoodRepository {
    override suspend fun getaFood(): Call<response> = foodApiService.getaFood()
}