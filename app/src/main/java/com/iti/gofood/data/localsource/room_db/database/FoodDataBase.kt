package com.iti.gofood.data.localsource.room_db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iti.gofood.data.localsource.room_db.dao.FoodDao
import com.iti.gofood.data.localsource.room_db.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1)
abstract class FoodDataBase : RoomDatabase() {
    abstract val dao: FoodDao
    companion object {
        @Volatile
        private var INSTANCE: FoodDataBase? = null

        fun getInstance(context: Context): FoodDataBase {
            return INSTANCE ?: synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context = context,
                    FoodDataBase::class.java,
                    "FoodDatabase"
                )
                    .build()
                    .also { INSTANCE = it
                    }
            }
        }
    }
}