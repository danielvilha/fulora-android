package com.fulora.app

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fulora.app.model.User
import com.fulora.app.source.local.FuloraDatabase
import com.fulora.app.source.local.dao.UserDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var db: FuloraDatabase
    private lateinit var userDao: UserDao

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
    }

    @Test
    fun useAppContext() {
        GlobalScope.launch {
            val user = User(
                name = "user",
                email = "email@email.com",
                password = "123"
            )

            userDao.insert(user)
            val retrievedUser = userDao.login(email = user.email, password = user.password)

            Assert.assertNotEquals(user.email, retrievedUser?.email)
        }
    }
}