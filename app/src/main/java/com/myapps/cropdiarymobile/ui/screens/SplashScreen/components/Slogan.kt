package com.myapps.cropdiarymobile.ui.screens.splashScreen.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.myapps.cropdiarymobile.core.WindowGrid
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.theme.SecondAppTypography

@Composable
internal fun Slogan(
    orientation: WindowOrientation,
    grid: WindowGrid,
    text: String,
    modifier: Modifier = Modifier,
    textStyle: androidx.compose.ui.text.TextStyle = SecondAppTypography.bodyLarge
) {
    val columns = if (orientation == WindowOrientation.Portrait) 7 else 5
    Text(
        text = text,
        modifier = modifier
            .width(grid.width(columns)),
        style = textStyle,
        textAlign = TextAlign.Center
    )
}