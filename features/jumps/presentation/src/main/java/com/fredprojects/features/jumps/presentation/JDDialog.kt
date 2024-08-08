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
import com.fredprojects.core.ui.R
import com.fredprojects.core.ui.*
import com.fredprojects.features.jumps.domain.models.JumpData
import com.fredprojects.features.jumps.domain.utils.JumpStatus
import com.fredprojects.features.jumps.presentation.vm.JDState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun JDDialog(jumpDataState: JDState, closeDialog: Action, upsertJD: (JumpData) -> Unit) {
    var countOfJumps by rememberSaveable { mutableStateOf("") }
    var isCountCorrect by rememberSaveable { mutableStateOf(true) }
    var date by rememberSaveable { mutableStateOf("") }
    var isDateCorrect by rememberSaveable { mutableStateOf(true) }
    if(jumpDataState.jd != null) {
        countOfJumps = jumpDataState.jd.count.toString()
        date = jumpDataState.jd.date.toString()
    }
    Dialog(closeDialog) {
        Column(Modifier.fillMaxWidth().background(MaterialTheme.colors.background), Arrangement.Center, Alignment.CenterHorizontally) {
            FredHeaderText(stringResource(R.string.update), textStyle = MaterialTheme.typography.h5)
            Spacer(Modifier.height(4.dp))
            FredNumericTextField(countOfJumps, { countOfJumps = it }, R.string.enterCount, isValueCorrect = isCountCorrect, imeAction = ImeAction.Next)
            if(!isCountCorrect) FredText(stringResource(R.string.error), color = MaterialTheme.colors.error)
            FredTextField(date, { date = it }, R.string.enterDate, isDateCorrect, ImeAction.Done, KeyboardType.Phone)
            if(!isDateCorrect) FredText(stringResource(R.string.incorrectDate), color = MaterialTheme.colors.error)
            Spacer(Modifier.height(4.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                JDIconButton(closeDialog, Icons.AutoMirrored.Default.KeyboardArrowLeft)
                JDIconButton(
                    {
                        isCountCorrect = countOfJumps.isNotBlank() && (countOfJumps.toIntOrNull() != null)
                        isDateCorrect = date.isNotBlank() && (date.toLocalDate() != null)
                        if(isCountCorrect && isDateCorrect) {
                            upsertJD(JumpData(countOfJumps.toInt(), date.toLocalDate() ?: LocalDate.now(), jumpDataState.jd?.id))
                            closeDialog()
                        }
                    },
                    Icons.Default.Done
                )
            }
        }
    }
    LaunchedEffect(key1 = true) {
        when(jumpDataState.jdStatus) {
            JumpStatus.INCORRECT_DATE -> isDateCorrect = false
            JumpStatus.INCORRECT_COUNT -> isCountCorrect = false
            JumpStatus.INCORRECT_DATA -> {
                isCountCorrect = false
                isDateCorrect = false
            }
            else -> {
                isCountCorrect = true
                isDateCorrect = true
            }
        }
    }
}
private fun String.toLocalDate() = try {
    if (this.contains(Regex("""^\d{4}-\d{2}-\d{2}$"""))) {
        val localDate = LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        if (localDate <= LocalDate.now()) localDate else null
    } else null
} catch (e: DateTimeParseException) {
    null
}