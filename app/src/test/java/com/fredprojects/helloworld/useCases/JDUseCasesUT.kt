package com.fredprojects.helloworld.useCases

import com.fredprojects.features.jumps.data.repositories.JDRepository
import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.useCases.JDUseCases
import com.fredprojects.features.jumps.domain.useCases.crud.*
import com.fredprojects.features.jumps.domain.utils.*
import com.fredprojects.helloworld.fakeDataSources.jumps.FakeJDDao
import junit.framework.TestCase.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class JDUseCasesUT {
    private lateinit var jdUseCases: JDUseCases
    @Before
    fun setUp() {
        val fakeJDDao = FakeJDDao()
        val jdRepository = JDRepository(fakeJDDao)
        val upsertJDUseCase = UpsertJDUseCase(jdRepository)
        val getJDUseCase = GetJDUseCase(jdRepository)
        val deleteJDUseCase = DeleteJDUseCase(jdRepository)
        jdUseCases = JDUseCases(getJDUseCase, upsertJDUseCase, deleteJDUseCase)
    }
    // upsertJDUseCase & getJDUseCase
    @Test
    fun success() = runTest(UnconfinedTestDispatcher()) {
        val jumpData = JumpData(1, LocalDate.now())
        val result = jdUseCases.upsert(jumpData)
        assertEquals(result, JumpStatus.SUCCESS)
        val jumpData2 = JumpData(2, LocalDate.now())
        val result2 = jdUseCases.upsert(jumpData2)
        assertEquals(result2, JumpStatus.SUCCESS)
        assertNotSame(jumpData, jumpData2)
        jdUseCases.getData(SortType.Ascending).collectLatest {
            assertNotNull(it.firstOrNull())
            assertNotNull(it.lastOrNull())
            assertEquals(it.first(), jumpData.copy(id = 0))
            assertEquals(it.last(), jumpData2.copy(id = 1))
        }
        val jumpData3 = JumpData(3, LocalDate.now(), 1)
        val result3 = jdUseCases.upsert(jumpData3)
        assertEquals(result3, JumpStatus.SUCCESS)
        jdUseCases.getData(SortType.Ascending).collectLatest {
            assertNotNull(it.firstOrNull())
            assertNotNull(it.lastOrNull())
            assertEquals(it.first(), jumpData.copy(id = 0))
            assertEquals(it.last(), jumpData3.copy(id = 1))
        }
    }
    @Test
    fun incorrectCount() = runTest(UnconfinedTestDispatcher()) {
        val jumpData = JumpData(-5, LocalDate.now(), 1)
        val result = jdUseCases.upsert(jumpData)
        assertEquals(result, JumpStatus.INCORRECT_COUNT)
    }
    @Test
    fun incorrectDate() = runTest(UnconfinedTestDispatcher()) {
        val jumpData = JumpData(1, LocalDate.now().plusYears(6L), 1)
        val result = jdUseCases.upsert(jumpData)
        assertEquals(result, JumpStatus.INCORRECT_DATE)
    }
    @Test
    fun incorrectData() = runTest(UnconfinedTestDispatcher()) {
        val jumpData = JumpData(0, LocalDate.now().plusYears(6L), 1)
        val result = jdUseCases.upsert(jumpData)
        assertEquals(result, JumpStatus.INCORRECT_DATA)
    }
    // deleteJDUseCase & getJDUseCase
    @Test
    fun delete() = runTest(UnconfinedTestDispatcher()) {
        val jumpData = JumpData(1, LocalDate.now())
        val result = jdUseCases.upsert(jumpData)
        assertEquals(result, JumpStatus.SUCCESS)
        jdUseCases.getData(SortType.Ascending).collectLatest {
            assert(it.isNotEmpty())
        }
        jdUseCases.delete(jumpData.copy(id = 0))
        jdUseCases.getData(SortType.Ascending).collectLatest {
            assert(it.isEmpty())
        }
    }
}