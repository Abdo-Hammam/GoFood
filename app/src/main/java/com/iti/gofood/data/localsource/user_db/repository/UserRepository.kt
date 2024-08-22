package com.iti.gofood.data.localsource.user_db.repository

import com.iti.gofood.data.localsource.user_db.dao.UserDao
import com.iti.gofood.data.localsource.user_db.entity.User


class UserRepository(private val userDao: UserDao) {


    fun insertUser(user: User) {
        userDao.addUser(user)
    }


    suspend fun isUserSignedUp(email: String, password: String): Boolean {
        return userDao.getUserByEmailAndPassword(email, password) != null
    }

}