package com.myapps.cropdiarymobile.domain.preferences.auth

import android.content.Context
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ClearLoginProviderUseCase @Inject constructor(
    private val repository: PreferencesRepository,
    private val dialogViewModel: DialogViewModel,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(): Boolean {
        return try {
            repository.clearSignInProvider()
            true
        } catch (e: Exception) {
            dialogViewModel.showDialog(
                dialogViewModel.error,
                context.getString(com.myapps.cropdiarymobile.R.string.an_error_occurred_while_clearing_the_login_provider_please_try_again)
            )
            false
        }
    }
}