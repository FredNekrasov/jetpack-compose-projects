package com.fredprojects.helloworld.useCases.auth

import com.fredprojects.features.auth.data.repository.UserRepository
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.domain.useCases.*
import com.fredprojects.features.auth.domain.utils.AuthStatus
import com.fredprojects.helloworld.fakeDataSources.auth.FakeUserDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpsertUserUseCaseUT {
    private lateinit var userUseCases: UserUseCases
    @Before
    fun setUp() {
        val fakeUserDao = FakeUserDao()
        val userRepository = UserRepository(fakeUserDao)
        val auth = AuthUseCase(userRepository)
        val upsert = UpsertUserUseCase(userRepository)
        val delete = DeleteUserUseCase(userRepository)
        userUseCases = UserUseCases(auth, upsert, delete)
    }
    @Test
    fun successRegister() = runTest(UnconfinedTestDispatcher()) {
        val user = User("username", "password", "email@example.com", "fred", "nekrasov")
        userUseCases.upsert(user).apply {
            assert(first == AuthStatus.SUCCESS)
            assert(second != null)
            assert(second == user.copy(id = 0))
        }
        userUseCases.auth("username", "password").let {
            assert(it.first == AuthStatus.SUCCESS)
            assert(it.second != null)
            assert(it.second == user.copy(id = 0))
        }
    }
    @Test
    fun incorrectRegisterUserName() = runTest(UnconfinedTestDispatcher()) {
        val user = User("  ", "password", "email@example.com", "fred", "nekrasov")
        userUseCases.upsert(user).apply {
            assert(first == AuthStatus.INVALID_USERNAME)
            assert(second == null)
        }
    }
    @Test
    fun incorrectRegisterPassword() = runTest(UnconfinedTestDispatcher()) {
        val user = User("username", "pass", "email@example.com", "fred", "nekrasov")
        userUseCases.upsert(user).apply {
            assert(first == AuthStatus.INVALID_PASSWORD)
            assert(second == null)
        }
    }
    @Test
    fun incorrectRegisterEmail() = runTest(UnconfinedTestDispatcher()) {
        val user = User("username", "password", "email", "fred", "nekrasov")
        userUseCases.upsert(user).apply {
            assert(first == AuthStatus.INVALID_EMAIL)
            assert(second == null)
        }
    }
    @Test
    fun successEditUserData() = runTest(UnconfinedTestDispatcher()) {
        val user = User("username", "password", "email@example.com", "fred", "nekrasov")
        userUseCases.upsert(user).apply {
            assert(first == AuthStatus.SUCCESS)
            assert(second != null)
        }
        userUseCases.auth("username", "password").let {
            assert(it.first == AuthStatus.SUCCESS)
            assert(it.second != null)
            assert(it.second == user.copy(id = 0))
        }
        userUseCases.upsert(user.copy(login = "fred", password = "12345678", id = 0)).apply {
            assert(first == AuthStatus.SUCCESS)
            assert(second != null)
        }
        userUseCases.auth("username", "password").let {
            assert(it.first == AuthStatus.INVALID_DATA)
            assert(it.second == null)
        }
        userUseCases.auth("fred", "12345678").let {
            assert(it.first == AuthStatus.SUCCESS)
            assert(it.second != null)
            assert(it.second == user.copy(login = "fred", password = "12345678", id = 0))
        }
    }
}