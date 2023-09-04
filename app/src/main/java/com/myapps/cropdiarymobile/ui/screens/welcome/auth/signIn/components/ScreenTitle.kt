package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ScreenTitle(modifier: Modifier = Modifier) {
    Text(
        text = "Login Page",
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}