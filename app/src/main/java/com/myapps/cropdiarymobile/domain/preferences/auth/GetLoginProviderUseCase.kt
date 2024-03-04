package com.myapps.cropdiarymobile.domain.preferences.auth

import android.content.Context
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetLoginProviderUseCase @Inject constructor(
    private val repository: PreferencesRepository,
    private val dialogViewModel: DialogViewModel,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(): ProviderType? {
        return try {
            repository.getSignInProvider()
        } catch (e: Exception) {
            dialogViewModel.showDialog(
                dialogViewModel.error,
                context.getString(R.string.an_error_occurred_while_getting_the_login_provider_please_try_again)
            )
            null
        }
    }
}