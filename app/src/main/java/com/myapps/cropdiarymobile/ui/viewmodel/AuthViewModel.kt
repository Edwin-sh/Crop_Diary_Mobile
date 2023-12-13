package com.myapps.cropdiarymobile.ui.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.myapps.cropdiarymobile.core.RequestCodes
import com.myapps.cropdiarymobile.core.TasksHelper
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.data.model.FirebaseUserModel
import com.myapps.cropdiarymobile.domain.auth.RecoveryPasswordUseCase
import com.myapps.cropdiarymobile.domain.auth.SignInWithEmailUseCase
import com.myapps.cropdiarymobile.domain.auth.SignInWithGoogleUseCase
import com.myapps.cropdiarymobile.domain.auth.SignOutUseCase
import com.myapps.cropdiarymobile.domain.auth.SignUpWithEmailUseCase
import com.myapps.cropdiarymobile.domain.preferences.auth.PutSignInProviderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val recoveryPasswordUseCase: RecoveryPasswordUseCase,
    private val putSignInProviderUseCase: PutSignInProviderUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val googleSignInOptions: GoogleSignInOptions,
    private val tasksHelper: TasksHelper,
    private val googleSignInClient: GoogleSignInClient,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {
    private val _authSignInModel: MutableLiveData<Result<FirebaseUser?>> = MutableLiveData()
    val authSignInModel:LiveData<Result<FirebaseUser?>> = _authSignInModel
    val authSignUpModel = MutableLiveData<Result<FirebaseUser?>>()
    val authResultModel = MutableLiveData<Result<Boolean>>()
    val _progressbar = MutableLiveData<Boolean>()

    //private val progressBarHelper = ProgressBarHelper(_progressbar)
    fun getGoogleSingInOptions(): GoogleSignInOptions {
        return googleSignInOptions
    }


    fun signInWithEmail(firebaseUserModel: FirebaseUserModel) {
        //progressBarHelper.isLoading(true)
        viewModelScope.launch {
            var result: Result<FirebaseUser?>? = signInWithEmailUseCase(firebaseUserModel)
            //progressBarHelper.isLoading(false)
            _authSignInModel.postValue(result!!)
        }
    }

    fun signUpWithEmail(firebaseUserModel: FirebaseUserModel) {
        //progressBarHelper.isLoading(true)
        viewModelScope.launch {
            var result: Result<FirebaseUser?> = signUpWithEmailUseCase(firebaseUserModel)
            authSignUpModel.postValue(result)
            //progressBarHelper.isLoading(false)
        }
    }

    fun signInWithGoogle(activity: Activity) {
        //progressBarHelper.isLoading(true)
        val client = GoogleSignIn.getClient(activity, googleSignInOptions)
        activity.startActivityForResult(client.signInIntent, RequestCodes.LOGIN_WITH_GOOGLE)
    }

    fun finishSignInWithGoogle(task: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            val accountResult = tasksHelper.getGoogleAuhResult(task)
            if (accountResult.isSuccess) {
                accountResult.getOrNull()?.idToken?.let { idToken ->
                    var result = signInWithGoogleUseCase(idToken)
                    if (result.isSuccess) {
                        result.getOrNull()?.let { firebaseUser ->
                            Log.i(
                                "AuthViewModel",
                                "Google sign with firebase success ${firebaseUser.email}"
                            )
                            putSignInProviderUseCase(ProviderType.GOOGLE)
                            _authSignInModel.postValue(result)
                            Log.i("AuthViewModel", "Posteando valor")
                        }
                    }
                }
                try {
                    googleSignInClient.signOut()
                    firebaseAuth.signOut()
                    //account.
                } catch (e: ApiException) {
                    Log.i("AuthViewModel", "Google sign in failed", e)
                }
            }

        }
    }

    fun recoveryPassword(email: String) {
        //progressBarHelper.isLoading(true)
        viewModelScope.launch {
            var result: Result<Boolean> = recoveryPasswordUseCase(email)
            //progressBarHelper.isLoading(false)
            authResultModel.postValue(result)
        }
    }

    fun signOut(
        provider: ProviderType,
        googleSignInClient: GoogleSignInClient
    ) {
        //progressBarHelper.isLoading(true)
        viewModelScope.launch {
            var result: Result<Boolean> = signOutUseCase(provider, googleSignInClient)
            //progressBarHelper.isLoading(false)
            authResultModel.postValue(result)
        }
    }
}