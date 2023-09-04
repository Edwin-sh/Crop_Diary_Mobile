package com.myapps.cropdiarymobile.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.myapps.cropdiarymobile.ui.theme.color.Black
import com.myapps.cropdiarymobile.ui.theme.color.BlackDisabled
import com.myapps.cropdiarymobile.ui.theme.color.BlackHighEmphasis
import com.myapps.cropdiarymobile.ui.theme.color.BlackMediumEmphasis
import com.myapps.cropdiarymobile.ui.theme.color.Error
import com.myapps.cropdiarymobile.ui.theme.color.Primary100
import com.myapps.cropdiarymobile.ui.theme.color.Primary200
import com.myapps.cropdiarymobile.ui.theme.color.Primary300
import com.myapps.cropdiarymobile.ui.theme.color.Primary50
import com.myapps.cropdiarymobile.ui.theme.color.Primary500
import com.myapps.cropdiarymobile.ui.theme.color.Primary900
import com.myapps.cropdiarymobile.ui.theme.color.Secondary300
import com.myapps.cropdiarymobile.ui.theme.color.Secondary500
import com.myapps.cropdiarymobile.ui.theme.color.Secondary900
import com.myapps.cropdiarymobile.ui.theme.color.White
import com.myapps.cropdiarymobile.ui.theme.color.WhiteDisabled
import com.myapps.cropdiarymobile.ui.theme.color.WhiteHighEmphasis
import com.myapps.cropdiarymobile.ui.theme.color.WhiteMediumEmphasis

private val darkColorScheme = lightColorScheme(
    primary = Primary900,
    secondary = Secondary900,
    inversePrimary = Secondary300,
    tertiary = Primary100,
    background = Black,
    onBackground = WhiteHighEmphasis,
    onSurface = WhiteMediumEmphasis,
    onPrimary = WhiteHighEmphasis,
    onSecondary = WhiteHighEmphasis,
    error = Error,
    outline = Primary300,
    outlineVariant = Secondary300
)

private val lightColorScheme = lightColorScheme(
    primary = Primary500,
    secondary = Secondary500,
    inversePrimary = Secondary500,
    tertiary = Primary100,
    background = White,
    onBackground = BlackHighEmphasis,
    onSurface = BlackMediumEmphasis,
    onPrimary = WhiteHighEmphasis,
    onSecondary = BlackHighEmphasis,
    error = Error,
    outline = Primary500,
    outlineVariant = Secondary500

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CropDiaryMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme else lightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
        shapes = Shapes
    )
}