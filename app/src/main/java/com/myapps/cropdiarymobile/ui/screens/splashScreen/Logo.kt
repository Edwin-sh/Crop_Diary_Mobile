package com.myapps.cropdiarymobile.ui.screens.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation

@Composable
internal fun Logo(orientation: WindowOrientation, modifier: Modifier = Modifier) {
    val weight = if (orientation == WindowOrientation.Landscape) 0.5f else 1f
    Image(
        painter = painterResource(id = R.drawable.splash_logo),
        contentDescription = "",
        modifier = modifier
            .fillMaxWidth(weight),
        contentScale = ContentScale.FillWidth
    )
}