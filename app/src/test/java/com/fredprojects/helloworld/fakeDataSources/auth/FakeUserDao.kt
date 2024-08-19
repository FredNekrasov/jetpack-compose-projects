package com.fredprojects.helloworld.fakeDataSources.auth

import com.fredprojects.core.database.dao.IUserDao
import com.fredprojects.core.database.entities.UserEntity

class FakeUserDao : IUserDao {
    private val users = mutableListOf<UserEntity>()
    override suspend fun get(
        login: String, password: String
    ): UserEntity? = users.find { it.login == login && it.password == password }
    override suspend fun upsert(user: UserEntity) {
        if(user.id == null) users.add(user.copy(id = users.size)) else users[user.id!!] = user
    }
    override suspend fun delete(userId: Int) {
        users.removeAt(userId)
    }
}