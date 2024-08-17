package com.iti.gofood.data.localsource.room_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val idMeal:Int,
    val strMeal:String,
    val strCategory:String,
    val strArea:String,
    val strInstructions:String,
    val strMealThumb:String,
    val strYoutube:String
)