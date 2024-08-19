package com.iti.gofood.data.repository

import com.iti.gofood.data.remotesource.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val foodRepository: FoodRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "www.themealdb.com/api/json/v1/1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log request and response body
    }

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(loggingInterceptor).build())
            .build()
            .create(ApiService::class.java)
    }

    override val foodRepository: FoodRepository by lazy {
        NetworkFoodRepository(apiService)
    }
}