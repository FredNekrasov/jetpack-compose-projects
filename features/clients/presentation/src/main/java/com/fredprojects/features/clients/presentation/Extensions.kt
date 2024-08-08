package com.fredprojects.features.clients.presentation

import com.fredprojects.core.ui.R
import com.fredprojects.features.clients.domain.utils.ActionStatus

fun ActionStatus.getString() = when(this) {
    ActionStatus.LOADING -> R.string.wait
    ActionStatus.SUCCESS -> R.string.success
    ActionStatus.CONNECTION_ERROR -> R.string.connectionError
    ActionStatus.NO_INTERNET -> R.string.noInternet
    ActionStatus.NO_DATA -> R.string.noData
    ActionStatus.SERIALIZATION_ERROR -> R.string.serializationError
    ActionStatus.UNKNOWN -> R.string.unknownError
    ActionStatus.NOTHING -> R.string.empty
}