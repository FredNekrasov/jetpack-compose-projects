package com.fredprojects.features.math.presentation.fib

import android.os.Binder
import com.fredprojects.features.math.domain.usecases.FibonacciUseCase
import com.fredprojects.features.math.domain.utils.CalculationStatus
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigInteger

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
    fun calculate(number: Int) {
        job = scope.launch {
            resultMSF.emit(fibonacciUseCase(number))
        }
    }
    fun cancel() {
        scopeToCancel.launch {
            job?.cancelAndJoin()
            resultMSF.emit(fibonacciUseCase(-1))
        }
    }
}