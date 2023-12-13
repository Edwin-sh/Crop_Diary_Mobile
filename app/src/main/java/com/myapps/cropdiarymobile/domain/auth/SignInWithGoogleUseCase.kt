package com.myapps.cropdiarymobile.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.myapps.cropdiarymobile.core.TasksHelper
import com.myapps.cropdiarymobile.data.auth.AuthService
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val service: AuthService,
    private val tasksHelper: TasksHelper
) {
    suspend operator fun invoke(idToken: String): Result<FirebaseUser?> {
        return tasksHelper.getAuthResult(service.signInWithGoogle(idToken))
    }
}