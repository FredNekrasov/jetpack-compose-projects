package com.fredprojects.helloworld.fakeDataSources.auth

import com.fredprojects.core.database.dao.IUserDao
import com.fredprojects.core.database.entities.UserEntity

class FakeUserDao : IUserDao {
    private val users = mutableListOf<UserEntity>()
    override suspend fun get(
        login: String, password: String
    ): UserEntity? = users.find { it.login == login && it.password == password }
    override suspend fun upsert(user: UserEntity) {
        user.id?.let { users[it] = user } ?: users.add(user.copy(id = users.size))
    }
    override suspend fun delete(userId: Int) {
        users.removeAt(userId)
    }
}