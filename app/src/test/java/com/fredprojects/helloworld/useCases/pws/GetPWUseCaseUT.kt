package com.fredprojects.helloworld.useCases.pws

import com.fredprojects.features.pws.data.repository.PWRepository
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.useCases.PWUseCases
import com.fredprojects.features.pws.domain.useCases.crud.*
import com.fredprojects.features.pws.domain.utils.*
import com.fredprojects.helloworld.fakeDataSources.pws.FakePWDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPWUseCaseUT {
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
    @Test
    fun getSortedListByDate() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(date = "11.12.2023"))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.Date(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(date = "11.12.2023", id = 1))
            assertEquals(it.last(), pw.copy(date = "11.11.2020", id = 0))
        }
        pwUseCases.getPWs(SortingPW.Date(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(date = "11.11.2020", id = 0))
            assertEquals(it.last(), pw.copy(date = "11.12.2023", id = 1))
        }
    }
    @Test
    fun getSortedListByPWName() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("abc", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(pwName = "oop"))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.PW(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(pwName = "oop", id = 1))
            assertEquals(it.last(), pw.copy(pwName = "abc", id = 0))
        }
        pwUseCases.getPWs(SortingPW.PW(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(pwName = "abc", id = 0))
            assertEquals(it.last(), pw.copy(pwName = "oop", id = 1))
        }
    }
    @Test
    fun getSortedListByStudent() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "alex", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(student = "fred"))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.Student(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(student = "fred", id = 1))
            assertEquals(it.last(), pw.copy(student = "alex", id = 0))
        }
        pwUseCases.getPWs(SortingPW.Student(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(student = "alex", id = 0))
            assertEquals(it.last(), pw.copy(student = "fred", id = 1))
        }
    }
    @Test
    fun getSortedListByVariant() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(variant = 4))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.Variant(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(variant = 4, id = 1))
            assertEquals(it.last(), pw.copy(variant = 3, id = 0))
        }
        pwUseCases.getPWs(SortingPW.Variant(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(variant = 3, id = 0))
            assertEquals(it.last(), pw.copy(variant = 4, id = 1))
        }
    }
    @Test
    fun getSortedListByLVL() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(level = 4))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.LVL(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(level = 4, id = 1))
            assertEquals(it.last(), pw.copy(level = 3, id = 0))
        }
        pwUseCases.getPWs(SortingPW.LVL(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(level = 3, id = 0))
            assertEquals(it.last(), pw.copy(level = 4, id = 1))
        }
    }
    @Test
    fun getSortedListByMark() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("oop", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(mark = 4))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs(SortingPW.Mark(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(mark = 5, id = 0))
            assertEquals(it.last(), pw.copy(mark = 4, id = 1))
        }
        pwUseCases.getPWs(SortingPW.Mark(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(mark = 4, id = 1))
            assertEquals(it.last(), pw.copy(mark = 5, id = 0))
        }
    }
    @Test
    fun getPWById() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(date = "11.12.2023"))
        assertEquals(result, PWStatus.SUCCESS)
        val pwFromRepo = pwUseCases.getPWs.getById(0)
        assertEquals(pwFromRepo, pw.copy(id = 0))
        val pwFromRepo2 = pwUseCases.getPWs.getById(1)
        assertEquals(pwFromRepo2, pw.copy(date = "11.12.2023", id = 1))
    }
    @Test
    fun getFilteredListByValue() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2020", 5, "image")
        var result = pwUseCases.upsert(pw)
        assertEquals(result, PWStatus.SUCCESS)
        result = pwUseCases.upsert(pw.copy(date = "11.12.2023"))
        assertEquals(result, PWStatus.SUCCESS)
        pwUseCases.getPWs.find("11", SortingPW.Date(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertEquals(it.first(), pw.copy(date = "11.12.2023", id = 1))
            assertEquals(it.last(), pw.copy(date = "11.11.2020", id = 0))
        }
    }
}