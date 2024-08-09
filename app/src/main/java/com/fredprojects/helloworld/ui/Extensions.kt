package com.fredprojects.helloworld.ui

import android.content.Context
import android.widget.Toast

fun Context.displayToast(textId: Int) {
    Toast.makeText(this, getString(textId), Toast.LENGTH_SHORT).show()
}