package com.myapps.cropdiarymobile.domain.connection

import android.content.Context
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.util.ConnectionChecker
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetConnectionStateUseCase @Inject constructor(
    private val dialogViewModel: DialogViewModel,
    private val connectionChecker: ConnectionChecker,
    @ApplicationContext private val context: Context
) {
    operator fun invoke(): Boolean {
        return try {
            if (!connectionChecker.isInternetAvailable()) {
                dialogViewModel.showDialog(
                    title = dialogViewModel.error,
                    message = context.getString(R.string.you_do_not_have_an_internet_connection)
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
                            message = context.getString(R.string.your_internet_connection_is_unstable_please_try_again)
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
                            firebaseStatus.getOrNull()!!
                        }
                    }
                }
            }
        } catch (e: Exception) {
            dialogViewModel.showDialog(
                title = dialogViewModel.error,
                message = context.getString(R.string.an_error_occurred_while_verifying_the_internet_connection)
            )
            false
        }
    }
}