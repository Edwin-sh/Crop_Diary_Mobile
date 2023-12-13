package com.myapps.cropdiarymobile.domain.auth

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.myapps.cropdiarymobile.core.TasksHelper
import com.myapps.cropdiarymobile.data.auth.AuthService
import com.myapps.cropdiarymobile.data.auth.ProviderType
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val service: AuthService,
    private val tasksHelper: TasksHelper
) {
    suspend operator fun invoke(
        provider: ProviderType,
        googleSignInClient: GoogleSignInClient
    ): Result<Boolean> {
        return try {
            service.signOut(provider, googleSignInClient)
            Result.success(true)
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }
}