package com.iti.gofood.data.remotesource.retrofit.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class response(val meals:List<Food>)

@Parcelize
data class Food(val idMeal:String,
                val strMeal:String,
                val strCategory:String,
                val strArea:String,
                val strInstructions:String,
                val strMealThumb:String,
                val strYoutube:String) : Parcelable