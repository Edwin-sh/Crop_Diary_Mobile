package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.ui.components.WindowGrid

@ExperimentalPagerApi
@Composable
internal fun FinishButton(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    text: String,
    grid: WindowGrid,
    orientation: WindowOrientation,
    style: TextStyle = TextStyle.Default,
    onClick: () -> Unit
) {
    val modifierOrientation = if (orientation == WindowOrientation.Portrait) modifier.size(
        width = grid.width(6),
        height = grid.height(1)
    ) else modifier.size(
        width = grid.width(3),
        height = grid.height(1)
    )
    Row(
        modifier = modifier
    ) {
        AnimatedVisibility(
            modifier = modifierOrientation.fillMaxWidth(),
            visible = pagerState.currentPage == pagerState.pageCount - 1
        ) {
            Button(
                onClick = onClick
            ) {
                Text(
                    text = text.uppercase(),
                    style = style
                )
            }
        }
    }
}
