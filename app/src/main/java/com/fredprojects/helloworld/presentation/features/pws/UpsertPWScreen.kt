package com.fredprojects.helloworld.presentation.features.pws

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.fredprojects.helloworld.domain.features.pws.models.PracticalWork
import com.fredprojects.helloworld.presentation.core.Action
import com.fredprojects.helloworld.presentation.features.pws.landscape.LandscapeUpsertPWScreen
import com.fredprojects.helloworld.presentation.features.pws.portrait.PortraitUpsertPWScreen
import com.fredprojects.helloworld.presentation.features.pws.vm.UpsertPWState

@Composable
fun UpsertPWScreen(
    state: UpsertPWState,
    goBack: Action,
    onTakePicture: () -> Uri,
    onUpsert: (PracticalWork) -> Unit
) {
    if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeUpsertPWScreen(state, goBack, onTakePicture, onUpsert)
    } else {
        PortraitUpsertPWScreen(state, goBack, onTakePicture, onUpsert)
    }
}