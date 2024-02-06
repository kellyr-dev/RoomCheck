package com.example.userroom.data.model

import androidx.lifecycle.LiveData

class UserRepository (private val userDao: UserDao) {

    val getAllUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun addUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

}