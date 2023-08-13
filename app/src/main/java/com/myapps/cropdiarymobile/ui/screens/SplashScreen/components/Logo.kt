package com.myapps.cropdiarymobile.ui.screens.splashScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowGrid
import com.myapps.cropdiarymobile.core.WindowOrientation

@Composable
internal fun Logo(orientation: WindowOrientation, grid: WindowGrid, modifier: Modifier = Modifier) {
    val columns = if (orientation == WindowOrientation.Portrait) 6 else 5
    val rows = if (orientation == WindowOrientation.Portrait) 4 else 5
    Image(
        painter = painterResource(id = R.drawable.splash_logo),
        contentDescription = "",
        modifier = modifier
            .width(grid.width(columns)),
        contentScale = ContentScale.FillWidth
    )
}