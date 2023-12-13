package com.myapps.cropdiarymobile.ui.screens.splashScreen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.myapps.cropdiarymobile.core.WindowGrid
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.theme.color.Error

@Composable
internal fun ProgressIndicator(
    orientation: WindowOrientation,
    grid: WindowGrid,
    modifier: Modifier = Modifier
) {
    val size = if (orientation == WindowOrientation.Portrait) grid.width(1.5) else grid.height(1.5)
    CircularProgressIndicator(
        modifier = modifier
            .size(size),
        color = Error,
        strokeWidth = grid.minimumSpace
    )
}