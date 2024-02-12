package com.myapps.cropdiarymobile.data.state

data class DialogStateData(
    val isShowing:  Boolean = false,
    val message: String = "",
    val title: String = "",
    val positiveButtonText: String = "",
    val negativeButtonText: String = "",
    val positiveButtonAction: () -> Unit = {},
    val negativeButtonAction: () -> Unit = {}
)
