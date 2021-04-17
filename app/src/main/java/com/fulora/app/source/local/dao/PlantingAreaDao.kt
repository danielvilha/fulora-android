package com.fulora.app.source.local.dao

import androidx.room.*
import com.fulora.app.model.PlantingArea
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantingAreaDao {
    @Insert
    suspend fun insert(plantingArea: PlantingArea)

    @Delete
    suspend fun delete(plantingArea: PlantingArea)

    @Update
    suspend fun update(plantingArea: PlantingArea)

    @Query("SELECT * FROM planting_area WHERE user_id = :userId ORDER BY name ASC")
    fun getAllPlantingAreas(userId: Int): Flow<List<PlantingArea>>

    @Query("SELECT * FROM planting_area WHERE id = :id")
    suspend fun getPlantingAreaById(id: Int): PlantingArea
}