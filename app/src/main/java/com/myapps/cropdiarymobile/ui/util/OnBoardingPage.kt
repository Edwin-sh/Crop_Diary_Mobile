package com.myapps.cropdiarymobile.ui.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.myapps.cropdiarymobile.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int
) {
    object First : OnBoardingPage(
        image = R.drawable.boarding_page_one,
        title = R.string.title_page_one,
        description = R.string.description_page_one
    )

    object Second : OnBoardingPage(
        image = R.drawable.boarding_page_two,
        title = R.string.title_page_two,
        description = R.string.description_page_two
    )

    object Third : OnBoardingPage(
        image = R.drawable.boarding_page_three,
        title = R.string.title_page_three,
        description = R.string.description_page_three
    )

    object Fourth : OnBoardingPage(
        image = R.drawable.boarding_page_four,
        title = R.string.title_page_four,
        description = R.string.description_page_four
    )
}
