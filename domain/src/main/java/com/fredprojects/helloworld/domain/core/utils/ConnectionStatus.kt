package com.fredprojects.helloworld.domain.core.utils

/**
 * ConnectionStatus represents the status of the connection to the server and the list of data received.
 *
 * @param list the list of data received
 * @param status the status of the connection
 *
 * @property Loading the status of the connection is loading
 * @property Success the status of the connection is success and the list of data received
 * @property Error the status of the connection is error
 */
sealed class ConnectionStatus<M>(
    private val list: List<M>,
    private val status: ActionStatus
) {
    class Loading<M>(list: List<M>): ConnectionStatus<M>(list, ActionStatus.LOADING)
    class Success<M>(list: List<M>): ConnectionStatus<M>(list, ActionStatus.SUCCESS)
    class Error<M>(list: List<M>, status: ActionStatus): ConnectionStatus<M>(list, status)
}