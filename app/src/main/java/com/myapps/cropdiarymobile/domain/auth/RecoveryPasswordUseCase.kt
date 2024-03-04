package com.myapps.cropdiarymobile.domain.auth

import com.myapps.cropdiarymobile.core.TasksHelper
import com.myapps.cropdiarymobile.data.auth.AuthService
import javax.inject.Inject

class RecoveryPasswordUseCase @Inject constructor(
    private val service: AuthService,
    private val tasksHelper: TasksHelper
) {
    suspend operator fun invoke(email: String): Boolean {
        return try {
            val task = service.recoveryPassword(email)
            tasksHelper.getVoidResult(task).isSuccess
        } catch (e: Exception) {
            false
        }
    }
}