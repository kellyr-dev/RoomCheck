package com.example.userroom.data.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao,
) {
    val getAllUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    fun searchUser(searchQuery: String): Flow<List<User>> = userDao.searchUser(searchQuery)
}
