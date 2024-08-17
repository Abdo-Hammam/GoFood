package com.iti.gofood.data.localsource.room_db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iti.gofood.data.localsource.room_db.entity.FoodEntity

@Dao
interface FoodDao {
    @Query("select * from food")
    fun getFoods(): LiveData<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFoods(vararg food: FoodEntity)
}