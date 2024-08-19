package com.fredprojects.helloworld.useCases.pws

import com.fredprojects.features.pws.data.repositories.PWRepository
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.domain.useCases.crud.*
import com.fredprojects.features.pws.domain.utils.*
import com.fredprojects.helloworld.fakeDataSources.pws.FakePWDao
import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeletePWUseCaseUT {
    private lateinit var pwUseCases: PWUseCases
    @Before
    fun setUp() {
        val fakePWDao = FakePWDao()
        val pwRepository = PWRepository(fakePWDao)
        val upsertPWUseCase = UpsertPWUseCase(pwRepository)
        val getPWUseCase = GetPWUseCase(pwRepository)
        val deletePWUseCase = DeletePWUseCase(pwRepository)
        pwUseCases = PWUseCases(getPWUseCase, upsertPWUseCase, deletePWUseCase)
    }
    // deletePWUseCase & getPWUseCase
    @Test
    fun delete() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2022", 5, "image")
        val result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.Date(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
        }
        pwUseCases.delete(pw.copy(id = 0))
        pwUseCases.getPWs(SortingPW.Date(SortType.Descending)).collectLatest {
            assert(it.isEmpty())
        }
    }
}