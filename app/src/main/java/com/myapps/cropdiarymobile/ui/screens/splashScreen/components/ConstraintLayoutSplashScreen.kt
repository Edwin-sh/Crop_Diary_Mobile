package com.myapps.cropdiarymobile.ui.screens.splashScreen.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.myapps.cropdiarymobile.core.WindowOrientation

@Composable
internal fun ConstraintLayoutSplashScreen(
    windowOrientation: WindowOrientation,
    constraints: ConstraintSet,
    modifier: Modifier = Modifier,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    BoxWithConstraints {
        ConstraintLayout(
            constraintSet = constraints,
            modifier = modifier
        ) {
            content()
        }
    }
}