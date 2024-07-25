package com.fredprojects.helloworld.presentation.features.inequality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.helloworld.R
import com.fredprojects.helloworld.domain.features.inequality.useCases.InequalityUseCase
import com.fredprojects.helloworld.domain.features.inequality.utils.InequalityStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * InequalityVM is used to provide data to the view
 * @param inequalityUseCase is used to solve the inequality
 */
class InequalityVM(
    private val inequalityUseCase: InequalityUseCase
) : ViewModel() {
    /**
     * @see solutionMSF is used to emit data to the state flow
     * @see solution is used to display data in the view
     */
    private val solutionMSF = MutableStateFlow<Any>("")
    val solution = solutionMSF.asStateFlow()
    /**
     * solveTheInequality is used to solve the inequality and emit data to the state flow
     */
    fun solveTheInequality(a: Float?, b: Float?) {
        viewModelScope.launch {
            val result = when(inequalityUseCase(a, b)) {
                InequalityStatus.ERROR -> R.string.error
                InequalityStatus.NO_SOLUTION -> R.string.inequalityHasNoSolutions
                InequalityStatus.FIRST_SOLUTION -> "$b < 0\nx = (− ∞ , + ∞)"
                InequalityStatus.SECOND_SOLUTION -> "x > ${-b!!/a!!}\nx = (${-b/a}, + ∞)"
                InequalityStatus.THIRD_SOLUTION -> "x < ${-b!!/a!!}\nx = (− ∞ , ${-b/a})"
            }
            solutionMSF.emit(result)
        }
    }
}