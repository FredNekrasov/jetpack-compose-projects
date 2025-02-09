package com.fredprojects.helloworld.useCases

import com.fredprojects.features.math.domain.usecases.FibonacciUseCase
import com.fredprojects.features.math.domain.utils.CalculationStatus.*
import junit.framework.TestCase.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import java.math.BigInteger

@OptIn(ExperimentalCoroutinesApi::class)
class FibUseCaseUT {
    private val fibUseCase = FibonacciUseCase()
    // correct inputs
    @Test
    fun solutionIsError(): Unit = runTest(UnconfinedTestDispatcher()) {
        val result = fibUseCase(-1)
        assertEquals(result, Pair(ERROR, BigInteger.ZERO))
    }
    @Test
    fun solutionIsSuccess(): Unit = runTest(UnconfinedTestDispatcher()) {
        val result = fibUseCase(1)
        val expected = 3
        assertEquals(result, Pair(SUCCESS, expected.toBigInteger()))
    }
    // incorrect inputs
    @Test
    fun solutionIsError2(): Unit = runTest(UnconfinedTestDispatcher()) {
        val result = fibUseCase(0)
        assertNotSame(result, Pair(ERROR, BigInteger.ZERO))
    }
    @Test
    fun solutionIsSuccess2(): Unit = runTest(UnconfinedTestDispatcher()) {
        val result = fibUseCase(-1)
        val expected = -3
        assertNotSame(result, Pair(SUCCESS, expected.toBigInteger()))
    }
}