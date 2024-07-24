package com.fredprojects.helloworld.presentation.features.fibonacci.binder

import android.os.Binder
import com.fredprojects.helloworld.domain.features.fibonacci.useCases.FibonacciUseCase
import com.fredprojects.helloworld.domain.features.fibonacci.utils.CalculationStatus
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigInteger

class FibonacciBinder(
    private val fibonacciUseCase: FibonacciUseCase
) : Binder() {
    private val scope by lazy { CoroutineScope(Dispatchers.Default) }
    private val scopeToCancel by lazy { CoroutineScope(Dispatchers.Default) }
    private val resultMSF = MutableStateFlow<Pair<CalculationStatus, BigInteger>>(CalculationStatus.INIT to BigInteger.ZERO)
    val result = resultMSF.asStateFlow()
    private var job: Job? = null
    fun calculate(number: Int) {
        job = scope.launch {
            delay(1000)
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