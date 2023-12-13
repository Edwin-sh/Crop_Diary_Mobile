package com.myapps.cropdiarymobile.domain.preferences.auth

import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import javax.inject.Inject

class GetSignInProviderUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(): ProviderType? {
        return repository.getSignInProvider()
    }
}