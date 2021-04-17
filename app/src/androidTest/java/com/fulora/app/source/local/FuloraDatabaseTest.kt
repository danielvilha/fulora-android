package com.fulora.app.source.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.fulora.app.model.Plant
import com.fulora.app.model.PlantingArea
import com.fulora.app.model.User
import com.fulora.app.source.local.dao.PlantDao
import com.fulora.app.source.local.dao.PlantingAreaDao
import com.fulora.app.source.local.dao.UserDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class FuloraDatabaseTest {
    private lateinit var db: FuloraDatabase
    private lateinit var userDao: UserDao
    private lateinit var plantingAreaDao: PlantingAreaDao
    private lateinit var plantDao: PlantDao
    private lateinit var testUser: User
    private lateinit var testPlantingArea: PlantingArea

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, FuloraDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        userDao = db.userDao
        plantingAreaDao = db.plantingAreaDao
        plantDao = db.plantDao

        runBlocking {
            userDao.insert(User(name = "Test", password = "test", email = "test"))
            testUser = userDao.login("test", "test")!!
            plantingAreaDao.insert(PlantingArea(name = "test", userId = testUser.id, picture = ""))
            testPlantingArea =plantingAreaDao.getAllPlantingAreas(testUser.id).first()[0]
        }
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun shouldReturnTheUser_WhenLoginIsPerformed_WithCorrectCredentials() {
        runBlocking {
            val user = User(
                name = "Name",
                email = "email@email.com",
                password = "123456"
            )
            userDao.insert(user)

            val retrievedUser = userDao.login(email = user.email, password = user.password)

            assertThat(retrievedUser?.name).isEqualTo(user.name)
        }
    }


    @Test
    fun shouldReturnNull_WhenLoginIsPerformed_WithInvalidCredentials() {
        runBlocking {
            val user = User(
                name = "Name",
                email = "email@email.com",
                password = "123456"
            )
            userDao.insert(user)

            val retrievedUser = userDao.login(email = user.email, password = "secret123")

            assertThat(retrievedUser).isNull()
        }
    }

    @Test
    fun shouldCreateAPlantationArea_WhenInsertAPlantationArea() {
        runBlocking {
            val user = User(
                name = "Name",
                email = "email@email.com",
                password = "123456"
            )
            userDao.insert(user)
            val retrievedUser = userDao.login(email = user.email, password = user.password)
            val plantationArea = PlantingArea(
                name = "Area",
                picture = "",
                userId = retrievedUser?.id ?: 0
            )
            plantingAreaDao.insert(plantationArea)

            val retrieved = plantingAreaDao.getAllPlantingAreas(retrievedUser?.id ?: 0).first()[0]

            assertThat(retrieved.name).isEqualTo(plantationArea.name)
        }
    }

    @Test
    fun shouldReturnEmptyList_WhenUser_HasNoPlantingAreas() {
        runBlocking {
            val user = User(
                name = "Name",
                email = "email@email.com",
                password = "123456"
            )
            userDao.insert(user)
            val retrievedUser = userDao.login(email = user.email, password = user.password)

            val retrieved = plantingAreaDao.getAllPlantingAreas(retrievedUser?.id ?: 0).first()

            assertThat(retrieved).isEmpty()
        }
    }

    @Test
    fun shouldInsertPlant(){
        runBlocking {
            val plant = Plant(
                name = "Plant",
                picture = "",
                frequency = 2,
                time = "",
                water = 150,
                plantingAreaId = testPlantingArea.id
            )
            val plant2 = plant.copy(name = "Plant2")
            plantDao.insert(plant)
            plantDao.insert(plant2)
            val retrieved = plantDao.getAllPlants(testPlantingArea.id).first().map { it.name }

            assertThat(retrieved).containsExactlyElementsIn(listOf(plant.name, plant2.name))
        }
    }
}
