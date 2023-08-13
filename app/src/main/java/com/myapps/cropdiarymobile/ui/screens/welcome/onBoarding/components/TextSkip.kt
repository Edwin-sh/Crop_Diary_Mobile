package com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.myapps.cropdiarymobile.R

@Composable
internal fun TextSkip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Text(
        text = stringResource(R.string.skip),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier.clickable {
            onClick()
        }
    )
}