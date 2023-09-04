package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation

@Composable
internal fun SeparatorSingInMethods(modifier: Modifier = Modifier) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierSize =
        if (orientation == WindowOrientation.Portrait)
            modifier.width(grid.width(7))
        else modifier.height(grid.height(7))
    if (orientation == WindowOrientation.Portrait) {
        Row(
            modifier = modifierSize,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SeparatorLine()
            SeparatorText()
            SeparatorLine()
        }
    } else {
        Column(
            modifier = modifierSize,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SeparatorLine()
            SeparatorText()
            SeparatorLine()
        }
    }
}

@Composable
fun SeparatorLine() {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    Box(
        modifier = if (orientation == WindowOrientation.Portrait)
            Modifier
                .size(
                    grid.width(3),
                    grid.minimumSpace
                )
                .background(MaterialTheme.colorScheme.outline)
        else Modifier
            .size(grid.minimumSpace, grid.height(3))
            .background(MaterialTheme.colorScheme.outline)
    )
}

@Composable
private fun SeparatorText() {
    Text(text = "OR", style = MaterialTheme.typography.titleMedium)
}