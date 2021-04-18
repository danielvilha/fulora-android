package com.fulora.app.repositories

import com.fulora.app.model.PlantingArea
import com.fulora.app.source.local.FuloraDatabase
import kotlinx.coroutines.flow.Flow

class PlantingAreaRepository(
    private val fuloraDatabase: FuloraDatabase
) {
    suspend fun createPlantingArea(plantingArea: PlantingArea) {
        fuloraDatabase.plantingAreaDao.insert(plantingArea)
    }

    fun getAllPlantingAreas(userId: Int): Flow<List<PlantingArea>> {
        return fuloraDatabase.plantingAreaDao.getAllPlantingAreas(userId)
    }

    suspend fun getPlantingAreaById(plantingAreaId: Int): PlantingArea? {
        return fuloraDatabase.plantingAreaDao.getPlantingAreaById(plantingAreaId)
    }
}