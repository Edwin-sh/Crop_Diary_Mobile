package com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.navigation.Destinations
import com.myapps.cropdiarymobile.ui.navigation.LocalNavController
import com.myapps.cropdiarymobile.ui.screens.welcome.components.Header
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.ConstraintLayoutOnBoardingScreen
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.FinishButton
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.PageDescription
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.PageImage
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.PageTitle
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.Pager
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.PagerIndicator
import com.myapps.cropdiarymobile.ui.screens.welcome.onBoarding.components.TextSkip
import com.myapps.cropdiarymobile.ui.theme.CropDiaryAppTheme
import com.myapps.cropdiarymobile.ui.util.OnBoardingPage
import com.myapps.cropdiarymobile.ui.viewmodel.OnBoardingViewModel

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth
    )
    val pagerState = rememberPagerState()
    val constraints = constraints(
        windowOrientation = orientation,
        grid = grid
    )
    val padding = if (orientation == WindowOrientation.Portrait) grid.margin else grid.minimumSpace
    ConstraintLayoutOnBoardingScreen(
        constraintSet = constraints,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {

        TextSkip(
            onClick = { navigateSignInScreen(navController, onBoardingViewModel) },
            modifier = Modifier.layoutId(LayoutId.skip)
        )
        Header(
            modifier = Modifier
                .layoutId(LayoutId.header)
        )

        val constraintsPager = constraintsPager(orientation)
        Pager(
            pages = pages,
            pagerState = pagerState,
            constraintSet = constraintsPager,
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(LayoutId.pager)
        ) { position ->
            val onBoardingPage = pages[position]
            PageImage(
                painter = painterResource(id = onBoardingPage.image),
                modifier = Modifier
                    .layoutId(LayoutId.page_image)
            )
            PageTitle(
                text = stringResource(id = onBoardingPage.title),
                modifier = Modifier
                    .layoutId(LayoutId.page_title)
            )
            PageDescription(
                text = stringResource(id = onBoardingPage.description),
                modifier = Modifier
                    .layoutId(LayoutId.page_description)
            )
        }

        PagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .width(grid.width(4))
                .layoutId(LayoutId.page_indicator)
        )
        FinishButton(
            pagerState = pagerState,
            modifier = Modifier
                .layoutId(LayoutId.button)
        ) {
            navigateSignInScreen(navController, onBoardingViewModel)
        }
    }
}

private fun navigateSignInScreen(
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel
) {
    onBoardingViewModel.saveOnBoardingState(completed = true)
    navController.popBackStack()
    navController.navigate(Destinations.SignInScreen.route)
}


@ExperimentalPagerApi
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun Preview() {
    CropDiaryAppTheme {
        OnBoardingScreen()
    }
}
