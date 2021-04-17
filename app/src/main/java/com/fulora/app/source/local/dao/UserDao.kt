package com.fulora.app.source.local.dao

import androidx.room.*
import com.fulora.app.model.User
import com.fulora.app.model.PlantingArea
import kotlinx.coroutines.flow.Flow

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 *
 * Defines methods for using the Fulora class with Room.
 */
@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserBy(id: Int): User?
}