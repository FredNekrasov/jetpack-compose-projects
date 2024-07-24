package com.fredprojects.helloworld.presentation.core

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fredprojects.helloworld.R

typealias Action = () -> Unit
// text fields
@Composable
fun FredHeaderText(text: String, textStyle: TextStyle, modifier: Modifier = Modifier) {
    Text(text, modifier, fontFamily = FontFamily.Serif, style = textStyle)
}
@Composable
fun FredText(text: String, modifier: Modifier = Modifier) {
    Text(text, modifier, fontFamily = FontFamily.Serif)
}
@Composable
fun FredNumericTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    errorId: Int = R.string.error,
    isValueCorrect: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Number
) {
    OutlinedTextField(
        value,
        { if(((it.toIntOrNull() != null) && (it.toInt() >= 0)) || (it == "")) onValueChange(it) },
        label = { FredText(text = stringResource(labelId)) },
        supportingText = { if(!isValueCorrect) FredText(text = stringResource(errorId)) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        isError = isValueCorrect
    )
}
@Composable
fun FredTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelId: Int,
    errorId: Int,
    isValueCorrect: Boolean,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value,
        onValueChange,
        label = { FredText(text = stringResource(labelId)) },
        supportingText = { if(!isValueCorrect) FredText(text = stringResource(errorId)) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        isError = isValueCorrect
    )
}
// buttons
@Composable
fun FredButton(onClick: Action, text: String, modifier: Modifier = Modifier) {
    Button(onClick, modifier) { Text(text) }
}
@Composable
fun FredRadioButton(text: String, selected: Boolean, onSelect: Action) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onSelect)
        Spacer(Modifier.width(2.dp))
        Text(text = text)
    }
}
@Composable
fun FredIconButton(
    onClick: Action, icon: ImageVector, modifier: Modifier = Modifier
) {
    IconButton(onClick, modifier) { Icon(icon, icon.toString()) }
}
@Composable
fun FredFloatingActionButton(onClick: Action, icon: ImageVector) {
    FloatingActionButton(onClick) { Icon(icon, icon.toString()) }
}
// other
@Composable
fun FredCard(
    modifier: Modifier,
    mainColor: Color,
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
            drawRoundRect(color = mainColor, size = size, cornerRadius = CornerRadius(cornerRadius.toPx()))
            drawRoundRect(
                color = secondaryColor, topLeft = Offset(size.width - cutCornerSize.toPx(), - 100f),
                size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FredTopBar(
    condition: Boolean,
    goBack: Action,
    openDrawer: Action
) {
    TopAppBar(
        title = { FredText(text = stringResource(R.string.app_name)) },
        navigationIcon = {
            if(condition) FredIconButton(goBack, icon = Icons.Default.ArrowBackIosNew) else FredIconButton(openDrawer, icon = Icons.Default.Menu)
        },
        actions = {
            if(condition) FredIconButton(openDrawer, icon = Icons.Default.Menu)
        }
    )
}
@Composable
fun FredNavigationDrawerItem(text: String, selected: Boolean, onClick: Action) {
    NavigationDrawerItem(
        label = { FredText(text) },
        selected,
        onClick,
        Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = NavigationDrawerItemDefaults.colors()
    )
}