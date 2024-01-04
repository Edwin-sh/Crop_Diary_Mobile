package com.myapps.cropdiarymobile.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.util.Utilities
import com.myapps.cropdiarymobile.data.state.NetworkState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dialogViewModel: DialogViewModel,
) :
    ViewModel() {
    var state: NetworkState by mutableStateOf(NetworkState())
        private set

    fun getNetworkState(): Boolean {
        return if (!Utilities.networkConnection(context)) {
            dialogViewModel.setMessage(context.getString(R.string.you_dont_have_an_internet_connection))
            dialogViewModel.showDialog()
            false
        } else true
    }
}