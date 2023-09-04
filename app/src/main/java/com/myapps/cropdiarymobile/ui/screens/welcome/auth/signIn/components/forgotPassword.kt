package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.functionText

@Composable
internal fun ForgotPasswordText(modifier: Modifier = Modifier, onClick: () -> Unit) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    Column(
        modifier = modifier.width(grid.width(7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.forgot_your_password),
            style = MaterialTheme.typography.bodyLarge
        )
        functionText(
            text = stringResource(R.string.reset_password),
            onClick = { onClick },
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = grid.minimumSpace)
        )
    }
}