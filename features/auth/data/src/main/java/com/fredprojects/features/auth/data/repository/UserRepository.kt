package com.fredprojects.features.auth.data.repository

import com.fredprojects.core.database.dao.IUserDao
import com.fredprojects.features.auth.data.mapper.toDomain
import com.fredprojects.features.auth.data.mapper.toEntity
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.repository.IUserRepository

class UserRepository(
    private val dao: IUserDao
) : IUserRepository {
    override suspend fun login(login: String, password: String): User? = dao.get(login, password)?.toDomain()
    override suspend fun upsert(userData: User) = dao.upsert(userData.toEntity())
    override suspend fun delete(userId: Int) = dao.delete(userId)
}