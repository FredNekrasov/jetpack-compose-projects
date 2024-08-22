package com.fredprojects.features.jumps.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
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
import com.fredprojects.features.jumps.presentation.models.JDPModel
import com.fredprojects.features.jumps.presentation.vm.JDEvents
import com.fredprojects.features.jumps.presentation.vm.JDState

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
        Column(Modifier.fillMaxWidth().background(MaterialTheme.colors.background), Arrangement.Center, Alignment.CenterHorizontally) {
            FredHeaderText(stringResource(R.string.update), textStyle = MaterialTheme.typography.h5)
            Spacer(Modifier.height(4.dp))
            FredNumericTextField(countOfJumps, { countOfJumps = it }, R.string.enterCount, isValuesCorrect, ImeAction.Next)
            FredTextField(date, { date = it }, R.string.enterDate, isValuesCorrect, ImeAction.Done, KeyboardType.Phone)
            if(!isValuesCorrect) {
                FredHeaderText(
                    "${stringResource(R.string.error)}\n${stringResource(R.string.incorrectDate)}",
                    textStyle = MaterialTheme.typography.body2, color = MaterialTheme.colors.error
                )
            }
            Spacer(Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                JDIconButton({ onEvent(JDEvents.SwitchingDialog) }, Icons.AutoMirrored.Default.KeyboardArrowLeft)
                JDIconButton(
                    {
                        onEvent(
                            JDEvents.UpsertJD(
                                JDPModel(countOfJumps.toIntOrNull(), date, jumpDataState.jd?.id)
                            )
                        )
                    },
                    Icons.Default.Done
                )
            }
        }
    }
}