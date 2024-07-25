package com.fredprojects.helloworld.presentation.features.jumps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fredprojects.helloworld.presentation.R
import com.fredprojects.helloworld.domain.features.jumps.models.JumpData
import com.fredprojects.helloworld.domain.features.jumps.utils.JumpStatus
import com.fredprojects.helloworld.presentation.core.*
import com.fredprojects.helloworld.presentation.features.jumps.vm.JDEvents
import com.fredprojects.helloworld.presentation.features.jumps.vm.JDState
import java.time.LocalDate

@Composable
internal fun JDDialog(
    state: JDState,
    onEvent: (JDEvents) -> Unit
) {
    var countOfJumps by rememberSaveable { mutableStateOf(state.jd?.count.toString()) }
    var isCountCorrect by rememberSaveable { mutableStateOf(true) }
    var date by rememberSaveable { mutableStateOf(state.jd?.date.toString()) }
    var isDateCorrect by rememberSaveable { mutableStateOf(true) }
    Dialog({ onEvent(JDEvents.SwitchingDialog) }) {
        Column(Modifier.wrapContentSize().background(MaterialTheme.colorScheme.background), Arrangement.Center, Alignment.CenterHorizontally) {
            FredHeaderText(stringResource(R.string.update), MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(4.dp))
            FredNumericTextField(countOfJumps, { countOfJumps = it }, R.string.enterCount, isValueCorrect = isCountCorrect)
            Spacer(Modifier.height(2.dp))
            FredTextField(date, { date = it }, R.string.enterDate, R.string.incorrectDate, isDateCorrect, ImeAction.Done)
            Spacer(Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                JDIconButton({ onEvent(JDEvents.SwitchingDialog) }, Icons.Default.ArrowBackIosNew)
                JDIconButton(
                    {
                        onEvent(JDEvents.UpsertJD(JumpData(countOfJumps.toIntOrNull() ?: 0, date.toLocalDate() ?: LocalDate.now(), state.jd?.id)))
                        when(state.jdStatus) {
                            JumpStatus.SUCCESS -> onEvent(JDEvents.SwitchingDialog)
                            JumpStatus.INCORRECT_DATE -> isDateCorrect = false
                            JumpStatus.INCORRECT_COUNT -> isCountCorrect = false
                            JumpStatus.INCORRECT_DATA -> {
                                isCountCorrect = false
                                isDateCorrect = false
                            }
                        }
                    },
                    Icons.Default.Done
                )
            }
        }
    }
}