package com.myapps.cropdiarymobile.domain.connection

import com.myapps.cropdiarymobile.core.util.ConnectionChecker
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import javax.inject.Inject

class GetConnectionStateUseCase @Inject constructor(
    private val dialogViewModel: DialogViewModel,
    private val connectionChecker: ConnectionChecker
) {


    operator fun invoke(): Boolean {
        return try {
            if (!connectionChecker.isInternetAvailable()) {
                dialogViewModel.showDialog(
                    title = dialogViewModel.error,
                    message = connectionChecker.notInternetConnectionMessage
                )
                false
            } else {
                val status = connectionChecker.isInternetReachable()
                if (status.isFailure) {
                    dialogViewModel.showDialog(
                        title = dialogViewModel.error,
                        message = status.exceptionOrNull()?.message.toString()
                    )
                    false
                } else {
                    if (!status.getOrNull()!!) {
                        dialogViewModel.showDialog(
                            title = dialogViewModel.error,
                            message = connectionChecker.notInternetReachableMessage
                        )
                        false
                    } else {
                        val firebaseStatus = connectionChecker.isFirebaseAvailable()
                        if (firebaseStatus.isFailure) {
                            dialogViewModel.showDialog(
                                title = dialogViewModel.error,
                                message = firebaseStatus.exceptionOrNull()?.message.toString()
                            )
                            false
                        } else {
                            if (!firebaseStatus.getOrNull()!!) {
                                dialogViewModel.showDialog(
                                    title = dialogViewModel.error,
                                    message = connectionChecker.notFirebaseConnectionReachable
                                )
                                return false
                            }
                            true
                        }
                    }
                }
            }
        } catch (e: Exception) {
            dialogViewModel.showDialog(
                title = dialogViewModel.error,
                message = connectionChecker.exceptionWhileVerifyingInternetConnectionMessage
            )
            false
        }
    }
}