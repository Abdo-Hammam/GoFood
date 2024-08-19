package com.iti.gofood.data.remotesource.retrofit

import com.iti.gofood.data.remotesource.retrofit.models.response
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("random.php")
    fun getaFood(): Call<response>
}