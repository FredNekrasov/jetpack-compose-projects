package com.fredprojects.helloworld.useCases

import com.fredprojects.features.math.domain.usecases.InequalityUseCase
import com.fredprojects.features.math.domain.utils.InequalityStatus
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Test

class InequalityUseCaseUT {
    private val inequalityUseCase = InequalityUseCase()
    // correct inputs
    @Test
    fun solutionIsError() {
        val result = inequalityUseCase(null, null)
        assertEquals(result, InequalityStatus.ERROR)
    }
    @Test
    fun solutionIsNoSolution() {
        val result = inequalityUseCase(0f, 0f)
        assertEquals(result, InequalityStatus.NO_SOLUTION)
    }
    @Test
    fun solutionIsFirstSolution() {
        val result = inequalityUseCase(0f, -1f)
        assertEquals(result, InequalityStatus.FIRST_SOLUTION)
    }
    @Test
    fun solutionIsSecondSolution() {
        val result = inequalityUseCase(-1f, 0f)
        assertEquals(result, InequalityStatus.SECOND_SOLUTION)
    }
    @Test
    fun solutionIsThirdSolution() {
        val result = inequalityUseCase(1f, 1f)
        assertEquals(result, InequalityStatus.THIRD_SOLUTION)
    }
    // incorrect inputs
    @Test
    fun solutionIsError2() {
        val result = inequalityUseCase(0f, 0f)
        assertNotSame(result, InequalityStatus.ERROR)
    }
    @Test
    fun solutionIsNoSolution2() {
        val result = inequalityUseCase(-1f, 0f)
        assertNotSame(result, InequalityStatus.NO_SOLUTION)
    }
    @Test
    fun solutionIsFirstSolution2() {
        val result = inequalityUseCase(0f, 1f)
        assertNotSame(result, InequalityStatus.FIRST_SOLUTION)
    }
    @Test
    fun solutionIsSecondSolution2() {
        val result = inequalityUseCase(1f, 0f)
        assertNotSame(result, InequalityStatus.SECOND_SOLUTION)
    }
    @Test
    fun solutionIsThirdSolution2() {
        val result = inequalityUseCase(-2f, 1f)
        assertNotSame(result, InequalityStatus.THIRD_SOLUTION)
    }
}