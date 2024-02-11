package com.myapps.cropdiarymobile.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel

@Composable
fun BasicAppDialog(
    modifier: Modifier = Modifier,
    dialogViewModel: DialogViewModel = hiltViewModel()
) {
    if (dialogViewModel.state.isShowing) {
        AppDialog(
            modifier = modifier,
            dialogState = true,
            text = dialogViewModel.state.message,
            title = dialogViewModel.state.title,
            confirmText = dialogViewModel.state.positiveButtonText,
            onConfirm = {dialogViewModel.hideDialog()},
            onDismiss = {}
        )
    }
}