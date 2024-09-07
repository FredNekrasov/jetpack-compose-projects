package com.fredprojects.helloworld.ui

import android.content.Context
import android.widget.Toast
import com.fredprojects.core.ui.R
import com.fredprojects.features.auth.domain.utils.AuthStatus
import com.fredprojects.features.clients.domain.utils.ActionStatus

fun Context.displayToast(textId: Int) {
    Toast.makeText(this, getString(textId), Toast.LENGTH_SHORT).show()
}
fun ActionStatus.isLoading() = this == ActionStatus.LOADING || this == ActionStatus.NOTHING
fun AuthStatus.onStatus(
    onError: (Boolean) -> Unit, context: Context, onSuccess: () -> Unit
) = when(this) {
    AuthStatus.SUCCESS -> onSuccess()
    AuthStatus.EXISTING_DATA -> {
        context.displayToast(R.string.existingUser)
        onError(false)
    }
    AuthStatus.INVALID_DATA -> {
        context.displayToast(R.string.error)
        onError(false)
    }
    AuthStatus.NOTHING -> onError(true)
    else -> onError(false)
}