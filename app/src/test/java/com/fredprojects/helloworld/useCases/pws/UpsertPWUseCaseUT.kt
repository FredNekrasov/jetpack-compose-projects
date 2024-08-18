package com.fredprojects.helloworld.useCases.pws

import com.fredprojects.features.pws.data.repositories.PWRepository
import com.fredprojects.features.pws.domain.models.PracticalWork
import com.fredprojects.features.pws.domain.useCases.crud.GetPWUseCase
import com.fredprojects.features.pws.domain.useCases.crud.UpsertPWUseCase
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
class UpsertPWUseCaseUT {
    private lateinit var upsertPW: UpsertPWUseCase
    private lateinit var getPWs: GetPWUseCase
    @Before
    fun setUp() {
        val fakePWDao = FakePWDao()
        val pwRepository = PWRepository(fakePWDao)
        upsertPW = UpsertPWUseCase(pwRepository)
        getPWs = GetPWUseCase(pwRepository)
    }
    @Test
    fun success() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2022", 5, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.SUCCESS)
        val pw2 = PracticalWork("oop", "qwerty", 3, 3, "11.11.2023", 5, "image")
        val result2 = upsertPW(pw2)
        assertEquals(result2, PWStatus.SUCCESS)
        assertNotSame(pw, pw2)
        getPWs(SortingPW.Date(SortType.Ascending)).collectLatest {
            assert(it.isNotEmpty())
            assertNotNull(it.firstOrNull())
            assertNotNull(it.lastOrNull())
            assertEquals(it.first(), pw.copy(id = 0))
            assertEquals(it.last(), pw2.copy(id = 1))
        }
        val pw3 = PracticalWork("name", "student", 3, 3, "11.11.2020", 5, "image", 1)
        val result3 = upsertPW(pw3)
        assertEquals(result3, PWStatus.SUCCESS)
        getPWs(SortingPW.Date(SortType.Descending)).collectLatest {
            assert(it.isNotEmpty())
            assertNotNull(it.firstOrNull())
            assertNotNull(it.lastOrNull())
            assertEquals(it.first(), pw.copy(id = 0))
            assertEquals(it.last(), pw3.copy(id = 1))
        }
    }
    @Test
    fun incorrectPWName() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("", "student", 3, 3, "11.11.2022", 5, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_PW_NAME)
    }
    @Test
    fun incorrectStudent() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "", 3, 3, "11.11.2022", 5, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_STUDENT)
    }
    @Test
    fun incorrectVariant() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 0, 3, "11.11.2022", 5, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_VARIANT)
    }
    @Test
    fun incorrectLVL() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 0, "11.11.2022", 5, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_LEVEL)
    }
    @Test
    fun incorrectDate() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "", 5, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_DATE)
    }
    @Test
    fun incorrectMark() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2022", 0, "image")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_MARK)
    }
    @Test
    fun incorrectImage() = runTest(UnconfinedTestDispatcher()) {
        val pw = PracticalWork("name", "student", 3, 3, "11.11.2022", 5, "")
        val result = upsertPW(pw)
        assertEquals(result, PWStatus.INCORRECT_IMAGE)
    }
}