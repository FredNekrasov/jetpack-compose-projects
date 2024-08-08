package com.fredprojects.features.fibonacci.impl

import android.os.Binder
import com.fredprojects.features.fibonacci.api.useCases.FibonacciUseCase
import com.fredprojects.features.fibonacci.api.utils.CalculationStatus
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigInteger

/**
 * Fibonacci binder.
 * @see FibonacciUseCase
 */
class FibonacciBinder(
    private val fibonacciUseCase: FibonacciUseCase
) : Binder() {
    // scope for the calculations
    private val scope by lazy { CoroutineScope(Dispatchers.Default) }
    // scope for the cancellation of the calculations
    private val scopeToCancel by lazy { CoroutineScope(Dispatchers.Default) }
    private val resultMSF = MutableStateFlow<Pair<CalculationStatus, BigInteger>>(CalculationStatus.INIT to BigInteger.ZERO)
    val result = resultMSF.asStateFlow()
    private var job: Job? = null
    /**
     * Starts the calculation fibonacci sequence and emits the result
     * @see FibonacciUseCase
     */
    fun calculate(number: Int) {
        job = scope.launch {
            resultMSF.emit(fibonacciUseCase(number))
        }
    }
    /**
     * Cancels the calculation and emits [CalculationStatus.ERROR]
     * @see FibonacciUseCase
     */
    fun cancel() {
        scopeToCancel.launch {
            job?.cancelAndJoin()
            resultMSF.emit(fibonacciUseCase(-1))
        }
    }
}