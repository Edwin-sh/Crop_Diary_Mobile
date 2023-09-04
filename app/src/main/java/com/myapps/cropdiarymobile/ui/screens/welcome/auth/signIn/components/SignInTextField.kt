package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import com.myapps.cropdiarymobile.core.WindowOrientation
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.TextFieldWithIconAndLabel

@Composable
fun SignInTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation
    val modifierSize = modifier.width(grid.width(if (orientation == WindowOrientation.Portrait) 7 else 5))
    TextFieldWithIconAndLabel(
        value = value,
        onValueChange = onValueChange,
        label = label,
        icon = icon,
        modifier = modifierSize,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )
}