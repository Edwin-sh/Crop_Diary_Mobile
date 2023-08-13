package com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation

@Composable
internal fun PageDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierOrientation =
        if (orientation == WindowOrientation.Portrait) modifier.width(grid.width(7))
        else modifier.width(grid.width(7))

    Text(
        modifier = modifierOrientation,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Justify
    )
}