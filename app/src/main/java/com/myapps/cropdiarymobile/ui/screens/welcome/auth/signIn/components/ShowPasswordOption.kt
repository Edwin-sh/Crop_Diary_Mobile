package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ShowPasswordOption(
    showPassword: Boolean,
    modifier: Modifier = Modifier,
    onShowPasswordChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Checkbox(
            checked = showPassword,
            onCheckedChange = onShowPasswordChange,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(text = "Show Password")
    }
}