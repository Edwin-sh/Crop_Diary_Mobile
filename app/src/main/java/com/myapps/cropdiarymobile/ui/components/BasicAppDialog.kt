package com.myapps.cropdiarymobile.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.data.state.DialogState
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
            confirmText = stringResource(id = R.string.accept),
            onConfirm = {dialogViewModel.hideDialog()},
            onDismiss = {}
        )
    }
}