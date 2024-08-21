package com.fredprojects.helloworld.ui.previews.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.FredIconButton
import com.fredprojects.features.auth.presentation.*
import com.fredprojects.features.auth.presentation.models.UDPModel
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AuthCorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Authorization(isDataCorrect = true, onAuth = { _ ->  }) {}
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun AuthIncorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Authorization(isDataCorrect = false, onAuth = { _ ->  }) {}
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun RegistrationCorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Registration(user = null, isDataCorrect = true, goBack = { /*TODO*/ }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun RegistrationIncorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Registration(user = null, isDataCorrect = true, goBack = { /*TODO*/ }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun EditUDCorrectPreview() {
    val user = UDPModel("fred", "password", "email@example.com", "fred", "nekrasov")
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Registration(user = user, isDataCorrect = true, goBack = { /*TODO*/ }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun EditUDIncorrectPreview() {
    val user = UDPModel("", "", "", "fred", "nekrasov")
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Registration(user = user, isDataCorrect = false, goBack = { /*TODO*/ }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ProfilePreview() {
    val user = UDPModel("fred", "password", "email@example.com", "fred", "nekrasov")
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Profile(userData = user, onDelete = { _ ->  }, toEditScreen = {  }) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                    FredIconButton({  }, Icons.Default.ShoppingCart, tint = MaterialTheme.colors.onBackground)
                    FredIconButton({  }, Icons.Default.Favorite, tint = MaterialTheme.colors.onBackground)
                }
                Spacer(Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                    FredIconButton({  }, Icons.Default.Home, tint = MaterialTheme.colors.onBackground)
                    FredIconButton({  }, Icons.AutoMirrored.Default.Send, tint = MaterialTheme.colors.onBackground)
                    FredIconButton({  }, Icons.Default.Star, tint = MaterialTheme.colors.onBackground)
                    FredIconButton({  }, Icons.Default.DateRange, tint = MaterialTheme.colors.onBackground)
                }
            }
        }
    }
}