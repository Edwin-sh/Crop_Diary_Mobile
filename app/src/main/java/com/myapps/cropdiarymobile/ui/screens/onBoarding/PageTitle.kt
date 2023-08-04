package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.components.WindowGrid

@Composable
internal fun PageTitle(
    text: String,
    grid: WindowGrid,
    orientation: WindowOrientation,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default
) {
    val modifierOrientation =
        if (orientation == WindowOrientation.Portrait) modifier.fillMaxWidth() else modifier.width(
            grid.width(6)
        )

    Text(
        modifier = modifierOrientation,
        text = text,
        style = style,
        textAlign = TextAlign.Center
    )
}