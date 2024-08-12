package com.fredprojects.helloworld.ui

import android.content.Context
import android.widget.Toast
import com.fredprojects.features.clients.domain.utils.ActionStatus

fun Context.displayToast(textId: Int) {
    Toast.makeText(this, getString(textId), Toast.LENGTH_SHORT).show()
}
fun ActionStatus.isLoading() = this == ActionStatus.LOADING || this == ActionStatus.NOTHING