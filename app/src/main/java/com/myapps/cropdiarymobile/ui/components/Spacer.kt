package com.myapps.cropdiarymobile.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.myapps.cropdiarymobile.R

@Composable
fun MinimumVerticalSPacer() {
    Spacer(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.dp_1))
            .fillMaxWidth()
    )
}

@Composable
fun SmallVerticalSPacer() {
    Spacer(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.dp_2))
            .fillMaxWidth()
    )
}

@Composable
fun MediumVerticalSPacer() {
    Spacer(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.dp_5))
            .fillMaxWidth()
    )
}

@Composable
fun LargeVerticalSPacer() {
    Spacer(
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.dp_8))
            .fillMaxWidth()
    )
}

@Composable
fun VerticalSPacer(size: Dp) {
    Spacer(
        modifier = Modifier
            .height(size)
            .fillMaxWidth()
    )
}
@Composable
fun MinimumHorizontalSPacer() {
    Spacer(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.dp_1))
            .fillMaxHeight()
    )
}

@Composable
fun SmallHorizontalSPacer() {
    Spacer(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.dp_2))
            .fillMaxHeight()
    )
}

@Composable
fun MediumHorizontalSPacer() {
    Spacer(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.dp_5))
            .fillMaxHeight()
    )
}

@Composable
fun LargeHorizontalSPacer() {
    Spacer(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.dp_8))
            .fillMaxHeight()
    )
}

@Composable
fun HorizontalSPacer(size: Dp) {
    Spacer(
        modifier = Modifier
            .width(size)
            .fillMaxHeight()
    )
}