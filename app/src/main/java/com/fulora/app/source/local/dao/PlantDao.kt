package com.fulora.app.source.local.dao

import androidx.room.*
import com.fulora.app.model.Plant
import com.fulora.app.model.PlantingArea
import kotlinx.coroutines.flow.Flow

/**
 * Created by danielvilha on 17/04/21
 * https://github.com/danielvilha
 *
 * Defines methods for using the Fulora class with Room.
 */
@Dao
interface PlantDao {
    @Insert
    suspend fun insert(plantingArea: Plant)

    @Delete
    suspend fun delete(plantingArea: Plant)

    @Update
    suspend fun update(plantingArea: Plant)

    @Query("SELECT * FROM plant WHERE planting_area_id = :plantingAreaId ORDER BY name ASC")
    fun getAllPlants(plantingAreaId: Int): Flow<List<Plant>>

    @Query("SELECT * FROM plant WHERE id = :id")
    suspend fun getPlantById(id: Int): Plant
}