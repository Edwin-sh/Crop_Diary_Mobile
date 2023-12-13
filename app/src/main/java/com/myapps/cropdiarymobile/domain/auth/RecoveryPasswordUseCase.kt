package com.myapps.cropdiarymobile.domain.auth

import com.myapps.cropdiarymobile.data.auth.AuthService
import com.myapps.cropdiarymobile.core.TasksHelper
import javax.inject.Inject

class RecoveryPasswordUseCase @Inject constructor(
    private val service: AuthService,
    private val tasksHelper: TasksHelper
) {

    suspend operator fun invoke(email: String): Result<Boolean> {
        return tasksHelper.getVoidResult(service.recoveryPassword(email))
    }
}