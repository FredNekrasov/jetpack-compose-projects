package com.fredprojects.helloworld.domain.core.utils

/**
 * ActionStatus represents the status of an action that can be performed.
 * @property NOTHING means that the action has no status.
 * @property LOADING means that the action is in progress.
 * @property SUCCESS means that the action was successfully completed.
 * @property CONNECTION_ERROR means that the action failed due to a connection error.
 * @property NO_INTERNET means there is no internet connection.
 * @property NO_DATA means that the action failed due to no data.
 * @property SERIALIZATION_ERROR means that the data could not be serialized.
 * @property UNKNOWN means that the action failed for an unknown reason.
 */
enum class ActionStatus {
    NOTHING,
    LOADING,
    SUCCESS,
    CONNECTION_ERROR,
    NO_INTERNET,
    NO_DATA,
    SERIALIZATION_ERROR,
    UNKNOWN
}