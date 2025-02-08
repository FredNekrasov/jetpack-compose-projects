package com.fredprojects.helloworld.ui.previews.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fredprojects.core.ui.FredIconButton
import com.fredprojects.features.auth.domain.models.User
import com.fredprojects.features.auth.presentation.*
import com.fredprojects.helloworld.ui.theme.HelloWorldTheme

@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AuthCorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Authorization(isDataCorrect = true, onAuth = { _ ->  }) {}
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun AuthIncorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Authorization(isDataCorrect = false, onAuth = { _ ->  }) {}
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun RegistrationCorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Registration(user = null, isDataCorrect = true, goBack = {  }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun RegistrationIncorrectPreview() {
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Registration(user = null, isDataCorrect = false, goBack = {  }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun EditUDCorrectPreview() {
    val user = User("fred", "password", "email@example.com", "fred", "nekrasov")
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Registration(user = user, isDataCorrect = true, goBack = {  }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun EditUDIncorrectPreview() {
    val user = User("", "", "", "fred", "nekrasov")
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Registration(user = user, isDataCorrect = false, goBack = {  }) { _ ->  }
        }
    }
}
@Preview(
    group = "auth",
    showSystemUi = true,
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun ProfilePreview() {
    val user = User("fred", "password", "email@example.com", "fred", "nekrasov")
    HelloWorldTheme {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Profile(userData = user, onDelete = { _ ->  }, toEditScreen = {  }) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                    FredIconButton({  }, Icons.Default.ShoppingCart, tint = MaterialTheme.colorScheme.onBackground)
                    FredIconButton({  }, Icons.Default.Favorite, tint = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(Modifier.height(4.dp))
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceEvenly, Alignment.CenterVertically) {
                    FredIconButton({  }, Icons.Default.Home, tint = MaterialTheme.colorScheme.onBackground)
                    FredIconButton({  }, Icons.AutoMirrored.Default.Send, tint = MaterialTheme.colorScheme.onBackground)
                    FredIconButton({  }, Icons.Default.Star, tint = MaterialTheme.colorScheme.onBackground)
                    FredIconButton({  }, Icons.Default.DateRange, tint = MaterialTheme.colorScheme.onBackground)
                }
            }
        }
    }
}