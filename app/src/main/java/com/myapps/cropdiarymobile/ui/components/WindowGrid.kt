package com.myapps.cropdiarymobile.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowOrientation


data class WindowGrid(
    val minimumSpace: Dp,
    val margin: Dp,
    private val columnWidth: Dp,
    private val rowHeight: Dp
) {
    fun width(columns: Double): Dp = ((columnWidth.value * columns) + (minimumSpace.value * (columns - 1))).dp
    fun height(rows: Double): Dp = ((rowHeight.value * rows) + (rowHeight.value * (rows - 1))).dp
}


@Composable
fun getWindowGrid(): WindowGrid {
    val orientation = getWindowOrientation()
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp
    val margin = getMargin(windowOrientation = orientation, width = width)
    val minimumSpace = margin / 3
    val availableWidth = getAvailableWidth(
        windowOrientation = orientation,
        width = width,
        margin = margin,
        minimumSpace = minimumSpace
    )
    val availableHeight = getAvailableHeight(
        windowOrientation = orientation,
        height = height,
        margin = margin,
        minimumSpace = minimumSpace
    )
    val columnWidth = getColumnWidth(
        windowOrientation = orientation,
        availableWidth = availableWidth
    )

    val rowHeight = getRowHeight(
        windowOrientation = orientation,
        availableHeight = availableHeight
    )
    return WindowGrid(
        minimumSpace = minimumSpace.dp,
        margin = margin.dp,
        columnWidth = columnWidth.dp,
        rowHeight = rowHeight.dp
    )
}

@Composable
private fun getMargin(windowOrientation: WindowOrientation, width: Int) =
    if (windowOrientation == WindowOrientation.Portrait) (width.div(20)) else (width.div(
        30
    ))

@Composable
private fun getAvailableWidth(
    windowOrientation: WindowOrientation,
    width: Int,
    margin: Int,
    minimumSpace: Int
): Int {
    val columns = if (windowOrientation == WindowOrientation.Portrait) 7 else 11
    return width - ((margin * 2) + (minimumSpace * columns))
}

@Composable
private fun getAvailableHeight(
    windowOrientation: WindowOrientation,
    height: Int,
    margin: Int,
    minimumSpace: Int
): Int {
    val rows = if (windowOrientation == WindowOrientation.Portrait) 11 else 7
    return height - ((margin * 2) + (minimumSpace * rows))
}

private fun getColumnWidth(
    windowOrientation: WindowOrientation,
    availableWidth: Int
) = if (windowOrientation == WindowOrientation.Portrait) availableWidth / 8 else availableWidth / 12

private fun getRowHeight(
    windowOrientation: WindowOrientation,
    availableHeight: Int
) =
    if (windowOrientation == WindowOrientation.Portrait) availableHeight / 12 else availableHeight / 8