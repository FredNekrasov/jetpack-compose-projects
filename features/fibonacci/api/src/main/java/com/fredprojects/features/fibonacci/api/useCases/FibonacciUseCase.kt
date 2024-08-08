package com.fredprojects.features.fibonacci.api.useCases

import com.fredprojects.features.fibonacci.api.utils.CalculationStatus
import kotlinx.coroutines.yield
import java.math.BigInteger

//Программа вычисления 3^fn со всеми десятичными знаками, где n in 1..45, fn - числа фибоначчи.
class FibonacciUseCase {
    suspend operator fun invoke(number: Int): Pair<CalculationStatus, BigInteger> {
        if(number <= 0) return Pair(CalculationStatus.ERROR, BigInteger.ZERO)
        var result = BigInteger.ONE
        val fibonacciNumber = generateSequence(0 to 1) {
            it.second to it.first + it.second
        }.map { it.first }.take(number + 1).last()
        for (i in 1..fibonacciNumber) {
            yield()
            result = result.multiply(BigInteger.valueOf(3L))
        }
        return Pair(CalculationStatus.SUCCESS, result)
    }
}