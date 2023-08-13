package com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.util.OnBoardingPage

@ExperimentalPagerApi
@Composable
internal fun Pager(
    pages: List<OnBoardingPage>,
    pagerState: PagerState,
    constraintSet: ConstraintSet,
    modifier: Modifier = Modifier,
    content: @Composable PagerScope.(page: Int) -> Unit
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierOrientation =
        if (orientation == WindowOrientation.Portrait) modifier.height(grid.height(8)) else modifier.height(
            grid.height(5)
        )
    HorizontalPager(
        modifier = modifierOrientation.fillMaxSize(),
        count = pages.size,
        state = pagerState
    ) { position ->
        BoxWithConstraints(modifier = Modifier.fillMaxSize())
        {
            ConstraintLayout(
                constraintSet = constraintSet,
                modifier = Modifier.fillMaxSize()
            ) {
                content(position)
            }
        }
    }


}