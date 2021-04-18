package com.fulora.app.repositories

import com.fulora.app.model.User
import com.fulora.app.source.local.FuloraDatabase

class UserRepository(
    private val fuloraDatabase: FuloraDatabase
) {

    suspend fun createUser(user: User){
        fuloraDatabase.userDao.insert(user)
    }

    suspend fun login(email: String, password: String): User? {
        return fuloraDatabase.userDao.login(email, password)
    }
}