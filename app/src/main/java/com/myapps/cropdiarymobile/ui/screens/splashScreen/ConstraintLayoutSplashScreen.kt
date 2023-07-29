package com.myapps.cropdiarymobile.ui.screens.splashScreen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.myapps.cropdiarymobile.core.WindowOrientation

@Composable
fun ConstraintLayoutSplashScreen(
    windowOrientation: WindowOrientation,
    modifier: Modifier = Modifier,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    BoxWithConstraints {
        val constraints = Constraints(windowOrientation)
        ConstraintLayout(
            constraintSet = constraints,
            modifier = modifier
        ) {
            content()
        }
    }
}