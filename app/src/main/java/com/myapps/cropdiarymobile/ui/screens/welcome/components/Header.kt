package com.myapps.cropdiarymobile.ui.screens.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.ProgressLine

@Composable
internal fun Header(
    modifier: Modifier = Modifier
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierOrientation = if (orientation == WindowOrientation.Portrait) modifier.size(
        width = grid.width(6),
        height = grid.height(1)
    ) else modifier.size(
        width = grid.width(5),
        height = grid.width(1)
    )

    Column(
        modifier = modifierOrientation,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )
        ProgressLine(
            progress = 0.3f,
            modifier = Modifier
                .fillMaxWidth()
                .height(grid.minimumSpace)
        )
    }
}