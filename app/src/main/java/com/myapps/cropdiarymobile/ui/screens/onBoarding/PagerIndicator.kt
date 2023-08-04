package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.myapps.cropdiarymobile.ui.components.WindowGrid

@ExperimentalPagerApi
@Composable
internal fun PagerIndicator(
    grid: WindowGrid,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPagerIndicator(
        modifier = modifier,
        pagerState = pagerState,
        activeColor = MaterialTheme.colorScheme.outline,
        inactiveColor = MaterialTheme.colorScheme.outlineVariant,
        indicatorWidth = grid.width(1),
        indicatorHeight = grid.minimumSpace,
        spacing = grid.minimumSpace
    )
}