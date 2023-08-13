package com.myapps.cropdiarymobile.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap

@Composable
fun ProgressLine(
    progress: Float,
    modifier: Modifier = Modifier
        .fillMaxSize(),
    color: Color = MaterialTheme.colorScheme.outline,
    trackColor: Color = MaterialTheme.colorScheme.outlineVariant
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        // Calculate the start and end coordinates of the lines
        val startX = canvasHeight / 2
        val endX = canvasWidth - startX
        val startY = canvasHeight / 2f

        // Draw the background line
        drawLine(
            start = Offset(startX, startY),
            end = Offset(endX, startY),
            color = trackColor,
            strokeWidth = canvasHeight * 0.83f,
            cap = StrokeCap.Round
        )
        // Draw the progress line
        val progressX = startX + progress * (endX - startX)
        drawLine(
            start = Offset(startX, startY),
            end = Offset(progressX, startY),
            color = color,
            strokeWidth = canvasHeight,
            cap = StrokeCap.Round
        )
    }
}