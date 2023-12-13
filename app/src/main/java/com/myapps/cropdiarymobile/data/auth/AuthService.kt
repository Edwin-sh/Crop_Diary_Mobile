package com.myapps.cropdiarymobile.data.auth

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.myapps.cropdiarymobile.data.model.FirebaseUserModel
import javax.inject.Inject

class AuthService @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : IAuthService {
    override suspend fun signUpWithEmail(firebaseUserModel: FirebaseUserModel): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(
            firebaseUserModel.email, firebaseUserModel.password
        )
    }

    override suspend fun signInWithEmail(firebaseUserModel: FirebaseUserModel): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(
            firebaseUserModel.email, firebaseUserModel.password
        )
    }

    override suspend fun signInWithGoogle(idToken: String): Task<AuthResult> {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return firebaseAuth.signInWithCredential(credential)
    }

    override suspend fun recoveryPassword(email: String): Task<Void> {
        return firebaseAuth.sendPasswordResetEmail(email)
    }

    override suspend fun signOut(
        provider: ProviderType,
        googleSignInClient: GoogleSignInClient
    ) {
        //Firebase Auth SignOut
        firebaseAuth.signOut()
        if (provider == ProviderType.GOOGLE) {
            //SignOut Google
            googleSignInClient.signOut()
        }
    }
}