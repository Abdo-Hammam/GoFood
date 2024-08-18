package com.iti.gofood.data.remotesource.retrofit

import com.iti.gofood.data.remotesource.retrofit.models.Food
import com.iti.gofood.data.remotesource.retrofit.models.response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("random.php")
    fun getaProduct(): Call<response>
}

object RetrofitClient {
    private const val BASE_URL = "www.themealdb.com/api/json/v1/1/"
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
}