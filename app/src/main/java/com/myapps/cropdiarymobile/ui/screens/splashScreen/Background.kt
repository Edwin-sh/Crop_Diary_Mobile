package com.myapps.cropdiarymobile.ui.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
internal fun BackGround(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        painter = painter,
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )
}