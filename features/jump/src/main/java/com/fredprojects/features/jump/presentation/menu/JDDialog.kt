package com.fredprojects.features.jump.presentation.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fredprojects.core.ui.*
import com.fredprojects.core.ui.R
import com.fredprojects.features.jump.domain.models.JumpData
import com.fredprojects.features.jump.presentation.JDIconButton
import com.fredprojects.features.jump.presentation.menu.vm.JDEvents
import com.fredprojects.features.jump.presentation.menu.vm.JDState

@Composable
fun JDDialog(
    jumpDataState: JDState,
    onEvent: (JDEvents) -> Unit,
    isValuesCorrect: Boolean
) {
    var countOfJumps by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    if(jumpDataState.jd != null) {
        countOfJumps = jumpDataState.jd.count.toString()
        date = jumpDataState.jd.date
    }
    Dialog({ onEvent(JDEvents.SwitchingDialog) }) {
        Column(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background), Arrangement.Center, Alignment.CenterHorizontally) {
            FredHeaderText(stringResource(R.string.update), textStyle = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(4.dp))
            FredNumericTextField(countOfJumps, { countOfJumps = it }, R.string.enterCount, isValuesCorrect, ImeAction.Next)
            FredTextField(date, { date = it }, R.string.enterDate, isValuesCorrect, ImeAction.Done, KeyboardType.Phone)
            if(!isValuesCorrect) {
                FredHeaderText(
                    "${stringResource(R.string.error)}\n${stringResource(R.string.incorrectDate)}",
                    textStyle = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.error
                )
            }
            Spacer(Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                JDIconButton(Icons.AutoMirrored.Default.KeyboardArrowLeft) { onEvent(JDEvents.SwitchingDialog) }
                JDIconButton(Icons.Default.Done) {
                    onEvent(
                        JDEvents.UpsertJD(
                            JumpData(countOfJumps.toIntOrNull() ?: 0, date, jumpDataState.jd?.id)
                        )
                    )
                }
            }
        }
    }
}