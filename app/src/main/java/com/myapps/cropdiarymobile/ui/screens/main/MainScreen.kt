package com.myapps.cropdiarymobile.ui.screens.main

import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val provider: ProviderType? by viewModel.provider().observeAsState(null)
    Button(onClick = { /*TODO*/ }) {
        Text(
            text = "Hello World!, signed with ${provider?.name}"
        )
    }
}