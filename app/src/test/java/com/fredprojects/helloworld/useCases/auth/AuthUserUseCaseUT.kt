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
class AuthUserUseCaseUT {
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
    fun successLogin() = runTest(UnconfinedTestDispatcher()) {
        val user = User("username", "password", "email@mail", "fred", "nekrasov")
        userUseCases.upsert(user).let {
            assert(it.first == AuthStatus.SUCCESS)
            assert(it.second != null)
        }
        userUseCases.auth("username", "password").let {
            assert(it.first == AuthStatus.SUCCESS)
            assert(it.second != null)
            assert(it.second == user.copy(id = 0))
        }
    }
    @Test
    fun incorrectAuthUserName() = runTest(UnconfinedTestDispatcher()) {
        userUseCases.auth("", "password").also {
            assert(it.first == AuthStatus.INVALID_USERNAME)
            assert(it.second == null)
        }
    }
    @Test
    fun incorrectAuthPassword() = runTest(UnconfinedTestDispatcher()) {
        userUseCases.auth("username", "").also {
            assert(it.first == AuthStatus.INVALID_PASSWORD)
            assert(it.second == null)
        }
    }
}