package com.fredprojects.features.clients.domain.utils

/**
 * ConnectionStatus represents the status of the connection to the server and the list of data received.
 *
 * @param list the list of data received
 * @param status the status of the connection
 *
 * @property Nothing the status of the connection is nothing
 * @property Loading the status of the connection is loading
 * @property Success the status of the connection is success and the list of data received
 * @property Error the status of the connection is error
 */
sealed class ConnectionStatus<M>(val list: List<M>, val status: ActionStatus) {
    class Nothing<M> : ConnectionStatus<M>(emptyList(), ActionStatus.NOTHING)
    class Loading<M>(list: List<M>): ConnectionStatus<M>(list, ActionStatus.LOADING)
    class Success<M>(list: List<M>): ConnectionStatus<M>(list, ActionStatus.SUCCESS)
    class Error<M>(list: List<M>, status: ActionStatus): ConnectionStatus<M>(list, status)
}