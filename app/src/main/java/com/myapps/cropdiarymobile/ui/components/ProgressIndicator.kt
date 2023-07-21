package com.myapps.cropdiarymobile.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.*

@Composable
fun CircularProgressComponent(size: Dp, color: Color) {
    CircularProgressIndicator(
        modifier = Modifier
            .size(size).layoutId("progressIndicator"),
        color = color,
        strokeWidth = 4.dp
    )
}
