package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.components.WindowGrid

@Composable
internal fun PageImage(
    painter: Painter,
    grid: WindowGrid,
    orientation: WindowOrientation,
    modifier: Modifier = Modifier
) {
    val modifierSize = if (orientation == WindowOrientation.Portrait) modifier
        .width(grid.width(6)) else modifier.height(grid.height(5))
    val escale = if (orientation == WindowOrientation.Portrait) ContentScale.FillWidth else ContentScale.FillHeight

    Image(
        modifier = modifierSize,
        painter = painter,
        contentDescription = "Pager Image",
        contentScale = escale
    )
}