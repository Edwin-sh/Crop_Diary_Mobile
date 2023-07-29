package com.myapps.cropdiarymobile.ui.screens.splashScreen

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation

@Composable
fun ConstraintLayoutSplashScreen(
    windowOrientation: WindowOrientation,
    content: @Composable BoxWithConstraintsScope.() -> Unit
) {
    BoxWithConstraints {
        val constraints = Constraints(windowOrientation)
        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_margin))
        ) {
            content()
        }
    }
}