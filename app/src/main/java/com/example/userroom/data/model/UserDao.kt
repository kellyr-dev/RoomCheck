package com.example.userroom.data.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_tb ORDER BY id ASC")
    fun getAll(): LiveData<List<User>>

    @Query("SELECT * FROM user_tb WHERE firstName LIKE :searchQuery OR lastName LIKE :searchQuery")
    fun searchUser(searchQuery: String): Flow<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user : User)

}