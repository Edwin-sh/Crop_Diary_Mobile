package com.myapps.cropdiarymobile.data.auth

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.myapps.cropdiarymobile.data.model.FirebaseUserModel

interface IAuthService {
    suspend fun signUpWithEmail(firebaseUserModel: FirebaseUserModel): Task<AuthResult>

    suspend fun signInWithEmail(firebaseUserModel: FirebaseUserModel): Task<AuthResult>

    suspend fun signInWithGoogle(idToken: String): Task<AuthResult>

    suspend fun recoveryPassword(email: String): Task<Void>

    suspend fun signOut(
        provider: ProviderType,
        googleSignInClient: GoogleSignInClient
    )
}