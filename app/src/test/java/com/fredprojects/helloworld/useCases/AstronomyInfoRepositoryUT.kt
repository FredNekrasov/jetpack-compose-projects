package com.fredprojects.helloworld.useCases

import com.fredprojects.features.clients.data.repositories.AstronomyInfoRepository
import com.fredprojects.features.clients.domain.astronomy.repository.IAstronomyRepository
import com.fredprojects.features.clients.domain.utils.ActionStatus
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import com.fredprojects.helloworld.fakeDataSources.astronomy.*
import com.fredprojects.helloworld.ui.isLoading
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyInfoRepositoryUT {
    private lateinit var astronomyInfoRepository: IAstronomyRepository
    @Before
    fun setup() {
        val fakeAstronomyInfoDao = FakeAstronomyInfoDao()
        val fakeAstronomyInfoApi = FakeAstronomyInfoService()
        astronomyInfoRepository = AstronomyInfoRepository(fakeAstronomyInfoDao, fakeAstronomyInfoApi)
    }
    @Test
    fun loading() = runTest(UnconfinedTestDispatcher()) {
        val result = astronomyInfoRepository.getData("res", "res", 3f)
        result.flowOn(UnconfinedTestDispatcher()).first().let {
            assert(it is ConnectionStatus.Loading)
            assert(it.list.isEmpty())
            assert(it.status.isLoading())
        }
    }
    @Test
    fun success() = runTest(UnconfinedTestDispatcher()) {
        val result = astronomyInfoRepository.getData("res", "res", 3f)
        result.flowOn(UnconfinedTestDispatcher()).collectLatest {
            delay(1000)
            assert(it is ConnectionStatus.Success)
            assert(it.list.isNotEmpty())
            assertEquals(it.status, ActionStatus.SUCCESS)
        }
    }
    @Test
    fun error() = runTest(UnconfinedTestDispatcher()) {
        val result = astronomyInfoRepository.getData("", "", 2f)
        result.flowOn(UnconfinedTestDispatcher()).collectLatest {
            delay(1000)
            assert(it is ConnectionStatus.Error)
            assert(it.list.isEmpty())
            assertEquals(it.status, ActionStatus.NO_DATA)
        }
    }
}