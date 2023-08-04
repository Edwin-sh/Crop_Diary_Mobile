package com.myapps.cropdiarymobile.ui.screens.onBoarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.getWindowOrientation
import com.myapps.cropdiarymobile.ui.components.getWindowGrid
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen() {
    val grid = getWindowGrid()
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth
    )
    val pagerState = rememberPagerState()
    val orientation = getWindowOrientation()
    val constraints = constraints(
        windowOrientation = orientation,
        grid = grid
    )
    ConstraintLayoutOnBoardingScreen(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .padding(grid.margin)
    ) {

        TextSkip(
            text = stringResource(R.string.skip),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.layoutId(LayoutId.skip)
        )
        Header(
            grid = grid,
            orientation = orientation,
            textStyle = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .layoutId(LayoutId.header)
        )

        val constraintsPager = constraintsPager(orientation)
        Pager(
            pages = pages,
            pagerState = pagerState,
            constraintSet = constraintsPager,
            grid = grid,
            orientation = orientation,
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(LayoutId.pager)
        ) { position ->
            val onBoardingPage = pages[position]
            PageImage(
                painter = painterResource(id = onBoardingPage.image),
                grid = grid,
                orientation = orientation,
                modifier = Modifier
                    .layoutId(LayoutId.page_image)
            )
            PageTitle(
                text = stringResource(id = onBoardingPage.title),
                grid = grid,
                orientation = orientation,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .layoutId(LayoutId.page_title)
            )
            PageDescription(
                text = stringResource(id = onBoardingPage.description),
                grid = grid,
                orientation = orientation,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .layoutId(LayoutId.page_description)
            )
        }
        PagerIndicator(
            grid = grid,
            pagerState = pagerState,
            modifier = Modifier
                .width(grid.width(4))
                .layoutId(LayoutId.page_indicator)
        )
        FinishButton(
            pagerState = pagerState,
            text = "Finish",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .size(
                    width = grid.width(4),
                    height = grid.height(1)
                )
                .layoutId(LayoutId.button)
        ) {
            /*welcomeViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)*/
        }
    }
}


@ExperimentalPagerApi
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Preview() {
    CropDiaryAppTheme {
        OnBoardingScreen()
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true, widthDp = 892, heightDp = 412)
@Composable
fun PreviewPersonalizated() {
    CropDiaryAppTheme {
        OnBoardingScreen()
    }
}