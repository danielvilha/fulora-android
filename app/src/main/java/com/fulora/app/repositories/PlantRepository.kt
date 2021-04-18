package com.fulora.app.repositories

import com.fulora.app.model.Plant
import com.fulora.app.source.local.FuloraDatabase
import kotlinx.coroutines.flow.Flow

class PlantRepository(
    private val fuloraDatabase: FuloraDatabase
) {
    suspend fun insertPlant(plant: Plant){
        fuloraDatabase.plantDao.insert(plant)
    }

    suspend fun getPlantById(plantId: Int): Plant? {
        return fuloraDatabase.plantDao.getPlantById(plantId)
    }

    fun getAllPlants(plantingAreaId: Int): Flow<List<Plant>> {
        return fuloraDatabase.plantDao.getAllPlants(plantingAreaId)
    }
}