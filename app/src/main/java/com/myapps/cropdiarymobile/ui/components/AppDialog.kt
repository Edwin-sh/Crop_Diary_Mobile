package com.myapps.cropdiarymobile.ui.components

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.myapps.cropdiarymobile.core.getWindowInformation
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    dialogState: Boolean = false,
    title: String = "",
    text: String = "",
    icon: @Composable () -> Unit = {},
    confirmText: String = "",
    dismissText: String = "",
    onDismiss: () -> Unit,
    onConfirm:  () -> Unit,
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val dialogShape = MaterialTheme.shapes.medium

    if (dialogState) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            },
            text = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { onConfirm() }) {
                    Text(
                        text = confirmText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(
                        text = dismissText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            },
            shape = dialogShape,
            icon = icon
        )
    }
}

@Composable
@Preview(showBackground = true)
fun preview() {
    AppDialog(
        onConfirm = {},
        onDismiss = {},
        title = "Titulo",
        text = "Texto",
        confirmText = "Confirmar",
        dismissText = "Cancelar",
        dialogState = true
    )
}