package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.constraintlayout.compose.ConstraintSet
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.components.WindowGrid

internal fun constraints(windowOrientation: WindowOrientation, grid: WindowGrid): ConstraintSet {
    return ConstraintSet {
        val skip = createRefFor(LayoutId.skip)
        val header = createRefFor(LayoutId.header)
        val pager = createRefFor(LayoutId.pager)
        val indicator = createRefFor(LayoutId.page_indicator)
        val button = createRefFor(LayoutId.button)

        if (windowOrientation == WindowOrientation.Portrait) {
            constrain(header) {
                top.linkTo(
                    skip.bottom,
                    margin = grid.minimumSpace
                )
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        }
        else {
            constrain(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(skip.start)
                //bottom.linkTo(parent.bottom)
            }
        }
        constrain(skip) {
            top.linkTo(parent.top)
            end.linkTo(parent.end)
        }

        constrain(pager) {
            top.linkTo(
                header.bottom,
                margin = grid.minimumSpace
            )
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(indicator.top)
        }
        constrain(indicator) {
            top.linkTo(pager.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(button.top)
        }
        constrain(button) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }

}

internal fun constraintsPager(windowOrientation: WindowOrientation): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor(LayoutId.page_image)
        val title = createRefFor(LayoutId.page_title)
        val description = createRefFor(LayoutId.page_description)

        if (windowOrientation == WindowOrientation.Portrait) {
            constrain(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(title) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(description.top)
            }
            constrain(description) {
                top.linkTo(title.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        }
        else {
            constrain(image) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(title.start)
                bottom.linkTo(parent.bottom)
            }
            constrain(title) {
                top.linkTo(parent.top)
                start.linkTo(image.end)
                end.linkTo(parent.end)
                bottom.linkTo(description.top)
            }
            constrain(description) {
                top.linkTo(title.bottom)
                start.linkTo(title.start)
                end.linkTo(title.end)
                bottom.linkTo(parent.bottom)
            }
        }
    }

}
