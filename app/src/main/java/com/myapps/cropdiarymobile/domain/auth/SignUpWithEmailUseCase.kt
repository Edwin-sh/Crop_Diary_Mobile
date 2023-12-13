package com.myapps.cropdiarymobile.domain.auth

import com.google.firebase.auth.FirebaseUser
import com.myapps.cropdiarymobile.core.TasksHelper
import com.myapps.cropdiarymobile.data.auth.AuthService
import com.myapps.cropdiarymobile.data.model.FirebaseUserModel
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val service: AuthService,
    private val tasksHelper: TasksHelper
) {
    suspend operator fun invoke(firebaseUserModel: FirebaseUserModel): Result<FirebaseUser?> {
        return tasksHelper.getAuthResult(service.signUpWithEmail(firebaseUserModel))
    }
}