package com.myapps.cropdiarymobile.ui.screens.splashScreen

import androidx.constraintlayout.compose.ConstraintSet
import com.myapps.cropdiarymobile.core.WindowOrientation

internal fun Constraints(windowOrientation: WindowOrientation): ConstraintSet {
    return ConstraintSet {
        val logo = createRefFor("logo")
        val slogan = createRefFor("slogan")
        val progressIndicator = createRefFor("progressIndicator")

        when (windowOrientation) {
            WindowOrientation.Landscape -> {
                constrain(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(slogan.start)
                    bottom.linkTo(parent.bottom)
                }
                constrain(slogan) {
                    top.linkTo(logo.top)
                    start.linkTo(logo.end)
                    end.linkTo(parent.end)
                    //bottom.linkTo(progressIndicator.top)
                }
                constrain(progressIndicator) {
                    //top.linkTo(slogan.bottom)
                    start.linkTo(slogan.start)
                    end.linkTo(slogan.end)
                    bottom.linkTo(logo.bottom)
                }
            }

            else -> {
                constrain(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                constrain(slogan) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(progressIndicator.top)
                }
                constrain(progressIndicator) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            }
        }
    }

}
