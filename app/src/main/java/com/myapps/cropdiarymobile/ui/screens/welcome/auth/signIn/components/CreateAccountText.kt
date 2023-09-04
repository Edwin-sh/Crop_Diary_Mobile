package com.myapps.cropdiarymobile.ui.screens.welcome.auth.signIn.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.getWindowInformation
import com.myapps.cropdiarymobile.ui.components.functionText

@Composable
internal fun CreateAccountText(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val windowInformation = getWindowInformation()
    val grid = windowInformation.windowGrid
    val orientation = windowInformation.windowOrientation

    Row (modifier = modifier){
        Text(text = stringResource(R.string.don_t_have_an_account))
        functionText(
            text = stringResource(R.string.sign_up),
            onClick = { onClick },
            modifier = Modifier.padding(start = grid.minimumSpace)
        )
    }
}