package com.iti.gofood.data.localsource.user_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
)
