package com.myapps.cropdiarymobile.ui.screens.splashScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.theme.SecondAppTypography

@Composable
internal fun Slogan(
    orientation: WindowOrientation,
    modifier: Modifier = Modifier,
    textStyle: androidx.compose.ui.text.TextStyle = SecondAppTypography.bodyLarge
) {
    val weight = if (orientation == WindowOrientation.Landscape) 0.5f else 1f
    Text(
        text = stringResource(id = R.string.slogan),
        modifier = modifier
            .fillMaxWidth(weight),
        style = textStyle,
        textAlign = TextAlign.Center
    )
}