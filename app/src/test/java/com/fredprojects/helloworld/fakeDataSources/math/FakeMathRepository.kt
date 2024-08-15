package com.fredprojects.helloworld.fakeDataSources.math

import com.fredprojects.features.clients.domain.math.models.MathModel
import com.fredprojects.features.clients.domain.math.repository.IMathRepository
import com.fredprojects.features.clients.domain.utils.ActionStatus
import com.fredprojects.features.clients.domain.utils.ConnectionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMathRepository : IMathRepository {
    override fun getData(expression: String): Flow<ConnectionStatus<MathModel>> = flow {
        emit(ConnectionStatus.Loading(getCachedList()))
        if(expression.isBlank()) {
            emit(ConnectionStatus.Error(getCachedList(), ActionStatus.NO_DATA))
        } else emit(ConnectionStatus.Success(listOf(getCachedList(), listOf(MathModel(expression, expression))).flatten()))
    }
    private fun getCachedList(): List<MathModel> = listOf(
        MathModel("1+1", "2"),
        MathModel("1+2", "3"),
        MathModel("1+3", "4"),
        MathModel("1+4", "5"),
        MathModel("1+5", "6"),
        MathModel("1+6", "7"),
        MathModel("1+7", "8"),
        MathModel("1+8", "9"),
        MathModel("1+9", "10")
    )
}