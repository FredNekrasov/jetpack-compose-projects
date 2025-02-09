package com.fredprojects.features.math.presentation.inequality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fredprojects.core.ui.R
import com.fredprojects.features.math.domain.usecases.InequalityUseCase
import com.fredprojects.features.math.domain.utils.InequalityStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InequalityVM @Inject constructor(
    private val inequalityUseCase: InequalityUseCase
) : ViewModel() {
    private val solutionMSF = MutableStateFlow<Any>("")
    val solution = solutionMSF.asStateFlow()
    fun solveTheInequality(a: Float?, b: Float?) {
        viewModelScope.launch {
            val result = when(inequalityUseCase(a, b)) {
                InequalityStatus.ERROR -> R.string.inequalityError
                InequalityStatus.NO_SOLUTION -> R.string.inequalityHasNoSolutions
                InequalityStatus.FIRST_SOLUTION -> "$b < 0\nx = (− ∞ , + ∞)"
                InequalityStatus.SECOND_SOLUTION -> "x > ${-b!!/a!!}\nx = (${-b/a}, + ∞)"
                InequalityStatus.THIRD_SOLUTION -> "x < ${-b!!/a!!}\nx = (− ∞ , ${-b/a})"
            }
            solutionMSF.emit(result)
        }
    }
}