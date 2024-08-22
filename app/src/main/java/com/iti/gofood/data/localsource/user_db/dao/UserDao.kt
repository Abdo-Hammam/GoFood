package com.iti.gofood.data.localsource.user_db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iti.gofood.data.localsource.user_db.entity.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query(value = "DELETE FROM user")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?
}