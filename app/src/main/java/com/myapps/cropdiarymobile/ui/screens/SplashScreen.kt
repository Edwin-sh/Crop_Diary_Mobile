package com.myapps.cropdiarymobile.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.WindowSize
import com.myapps.cropdiarymobile.ui.components.CircularProgressComponent
import com.myapps.cropdiarymobile.ui.theme.SecondAppTypography
import com.myapps.cropdiarymobile.ui.theme.color.Error

@Composable
fun SplashViewScreen(windowSize: WindowSize) {
    val orientation =
        if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) WindowOrientation.Portrait else WindowOrientation.Landscape
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bkg_splash_view),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        BoxWithConstraints {
            val constraints = setConstraints(orientation)
            ConstraintLayout(
                constraintSet = constraints,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.dp_3))
            ) {
                getComponents(windowOrientation = orientation, windowSize = windowSize)
            }
        }
        /*Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.dp_3))
        ) {
            when (orientation) {
                WindowOrientation.Portrait -> {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight()
                                .padding(horizontal = dimensionResource(id = R.dimen.dp_3)),
                            contentAlignment = Alignment.Center
                        ) {
                            LogoSplashScreen()
                        }
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            SloganSplashSrcreen(textStyle = SecondAppTypography.bodyLarge)
                            LargeVerticalSPacer()
                            ProgressIndicator()
                        }
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = dimensionResource(id = R.dimen.dp_3)),
                                contentAlignment = Alignment.Center
                            ) { LogoSplashScreen() }
                        }
                        item { LargeVerticalSPacer() }
                        item { SloganSplashSrcreen(textStyle = SecondAppTypography.bodyLarge) }
                        item { LargeVerticalSPacer() }
                        item { ProgressIndicator() }
                    }
                }
            }
        }
*/
    }
}

private fun setConstraints(windowOrientation: WindowOrientation): ConstraintSet {
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

@Composable
private fun getComponents(windowOrientation: WindowOrientation, windowSize: WindowSize) {
    when (windowOrientation) {
        WindowOrientation.Portrait -> {
            LogoSplashScreen(windowOrientation)
            SloganSplashSrcreen(windowOrientation)
            ProgressIndicator(windowSize)
        }

        else -> {
            LogoSplashScreen(windowOrientation)
            SloganSplashSrcreen(windowOrientation)
            ProgressIndicator(windowSize)
        }
    }
}

@Composable
private fun LogoSplashScreen(orientation: WindowOrientation) {
    var weight = 1f
    if (orientation == WindowOrientation.Landscape) {
        weight = 0.5f
    }
    Image(
        painter = painterResource(id = R.drawable.splash_logo),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth(weight)
            .padding(horizontal = dimensionResource(id = R.dimen.dp_3))
            .layoutId("logo"),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun SloganSplashSrcreen(orientation: WindowOrientation) {
    var weight = 1f
    if (orientation == WindowOrientation.Landscape) {
        weight = 0.5f
    }
    val textStyle = SecondAppTypography.bodyLarge
    Text(
        text = stringResource(id = R.string.slogan),
        modifier = Modifier
            .fillMaxWidth(weight)
            .layoutId("slogan"),
        style = textStyle,
        textAlign = TextAlign.Center
    )
}


@Composable
private fun ProgressIndicator(windowSize: WindowSize) {
    var size = dimensionResource(id = R.dimen.dp_5)
    CircularProgressComponent(
        size = size,
        color = Error,
    )
}


