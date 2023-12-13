package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation

@Composable
internal fun SignInIcons(
    onFacebookClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onPhoneClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierSize =
        if (orientation == WindowOrientation.Portrait) modifier.fillMaxWidth()
        else modifier.height(grid.height(6.5))
    if (orientation == WindowOrientation.Portrait) {
        Row(
            modifier = modifierSize,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            socialIcons(
                onFacebookClick = onFacebookClick,
                onGoogleClick = onGoogleClick,
                onPhoneClick = onPhoneClick
            )
        }
    } else {
        Column(
            modifier = modifierSize,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            socialIcons(onFacebookClick = { onFacebookClick },
                onGoogleClick = { onGoogleClick },
                onPhoneClick = { onPhoneClick })
        }
    }
}

@Composable
private fun socialIcons(
    onFacebookClick: () -> Unit,
    onGoogleClick: () -> Unit,
    onPhoneClick: () -> Unit
) {
    SocialMediaIconButton(
        icon = painterResource(id = R.drawable.ic_facebook),
        label = stringResource(R.string.facebook),
        onClick = onFacebookClick
    )
    SocialMediaIconButton(
        icon = painterResource(id = R.drawable.ic_google),
        label = stringResource(R.string.google),
        onClick = onGoogleClick
    )
    SocialMediaIconButton(
        icon = painterResource(id = R.drawable.ic_phone),
        label = stringResource(R.string.phone),
        onClick = onPhoneClick
    )
}

@Composable
private fun SocialMediaIconButton(
    icon: Painter,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val iconSize =
        if (orientation == WindowOrientation.Portrait) grid.height(0.8)
        else grid.height(1.3)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = modifier
                .size(iconSize)
                .background(
                    MaterialTheme.colorScheme.tertiary,
                    MaterialTheme.shapes.medium
                )
                .padding(grid.minimumSpace)
                .clickable { onClick() }
        ) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}