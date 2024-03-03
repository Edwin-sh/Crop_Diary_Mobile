package com.myapps.cropdiarymobile.domain.preferences.onboarding

import android.content.Context
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetOnBoardingStateUseCase @Inject constructor(
    private val repository: PreferencesRepository,
    private val dialogViewModel: DialogViewModel,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(): Boolean {
        return try {
            repository.getOnBoardingState() == true
        } catch (e: Exception) {
            dialogViewModel.showDialog(
                title = dialogViewModel.error,
                message = "${context.getString(R.string.an_error_occurred_obtaining_the_onboarding_state)}: ${e.message}"
            )
            false
        }
    }
}