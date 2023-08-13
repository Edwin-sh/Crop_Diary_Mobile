package com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.BasicButton

@ExperimentalPagerApi
@Composable
internal fun FinishButton(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierOrientation = if (orientation == WindowOrientation.Portrait) modifier.size(
        width = grid.width(4),
        height = grid.width(1)
    ) else modifier.size(
        width = grid.width(3),
        height = grid.height(1) + grid.minimumSpace
    )
    Row(
        modifier = modifierOrientation
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = pagerState.currentPage == pagerState.pageCount - 1
        ) {
            BasicButton(
                onClick = onClick,
                text = stringResource(R.string.get_started),
                MaterialTheme.typography.titleMedium
            )

        }
    }
}
