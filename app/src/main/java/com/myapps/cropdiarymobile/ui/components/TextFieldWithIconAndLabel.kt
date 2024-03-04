package com.myapps.cropdiarymobile.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import com.myapps.cropdiarymobile.R

@Composable
fun TextFieldWithIconAndLabel(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    errorMessage: String = "",
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                softWrap = false
            )
        },
        placeholder = {
            Text(text = "${stringResource(R.string.enter_your)} ${label.lowercase()}")
        },
        supportingText = {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.secondary)
        },
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation
    )

}