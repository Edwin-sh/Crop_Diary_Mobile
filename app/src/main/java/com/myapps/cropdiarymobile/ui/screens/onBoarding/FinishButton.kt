package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
internal fun FinishButton(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = TextStyle.Default,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
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
