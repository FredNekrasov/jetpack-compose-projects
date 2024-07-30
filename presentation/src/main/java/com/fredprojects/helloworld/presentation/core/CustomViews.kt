package com.fredprojects.helloworld.presentation.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.presentation.R

typealias Action = () -> Unit
// text fields
@Composable
fun FredHeaderText(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text,
        modifier,
        color = color,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Center,
        style = textStyle
    )
}
@Composable
fun FredText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified
) {
    Text(
        text,
        modifier,
        color = color,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Justify
    )
}
@Composable
fun FredNumericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    isValueCorrect: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.NumberPassword
) {
    TextField(
        value,
        { if(((it.toIntOrNull() != null) && (it.toInt() >= 0)) || (it == "")) onValueChange(it) },
        label = { FredText(stringResource(labelId)) },
        isError = !isValueCorrect,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = MaterialTheme.colors.onBackground,
            focusedBorderColor = MaterialTheme.colors.onBackground,
            unfocusedBorderColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.onBackground
        )
    )
}
@Composable
fun FredTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    isValueCorrect: Boolean,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value,
        onValueChange,
        label = { FredText(stringResource(labelId))},
        isError = !isValueCorrect,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onBackground,
            backgroundColor = MaterialTheme.colors.background,
            cursorColor = MaterialTheme.colors.onBackground,
            focusedBorderColor = MaterialTheme.colors.onBackground,
            unfocusedBorderColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.onBackground
        )
    )
}
// buttons
@Composable
fun FredButton(onClick: Action, text: String, modifier: Modifier = Modifier) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.onBackground, MaterialTheme.colors.background)
    ) { FredText(text) }
}
@Composable
fun FredRadioButton(text: String, selected: Boolean, onSelect: Action) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(MaterialTheme.colors.primary, MaterialTheme.colors.onBackground)
        )
        FredText(text)
    }
}
@Composable
fun FredIconButton(
    onClick: Action,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    tint: Color = MaterialTheme.colors.background
) {
    IconButton(onClick, modifier) { Icon(icon, icon.toString(), tint = tint) }
}
@Composable
fun FredFloatingActionButton(icon: ImageVector, onClick: Action) {
    FloatingActionButton(
        onClick,
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background
    ) {
        Icon(icon, icon.toString())
    }
}
// other
@Composable
fun FredCard(
    modifier: Modifier,
    primaryColor: Color,
    secondaryColor: Color,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp
) {
    Canvas(modifier) {
        val clipPath = Path().apply {
            lineTo(size.width - cutCornerSize.toPx(), 0f)
            lineTo(size.width, cutCornerSize.toPx())
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        clipPath(clipPath) {
            drawRoundRect(color = primaryColor, size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
            drawRoundRect(
                color = secondaryColor, topLeft = Offset(size.width - cutCornerSize.toPx(), - 100f),
                size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )
        }
    }
}
@Composable
fun FredTopBar(isAction: Boolean, action: Action, isArrowUp: Boolean = false) {
    TopAppBar(
        title = { FredHeaderText(stringResource(R.string.app_name), textStyle = MaterialTheme.typography.h5) },
        navigationIcon = { if(!isAction) FredIconButton(action, Icons.AutoMirrored.Default.KeyboardArrowLeft) },
        actions = {
            when {
                isAction && !isArrowUp -> FredIconButton(action, Icons.Default.KeyboardArrowDown)
                isAction && isArrowUp -> FredIconButton(action, Icons.Default.KeyboardArrowUp)
            }
        },
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background
    )
}