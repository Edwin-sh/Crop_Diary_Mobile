package com.myapps.cropdiarymobile.core

import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.myapps.cropdiarymobile.core.ExceptionsHelper.authException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TasksHelper @Inject constructor() {
    suspend fun getAuthResult(task: Task<AuthResult>): Result<FirebaseUser?> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(task.await().user)
            } catch (ex: Exception) {
                Result.failure<FirebaseUser>(authException(ex))
            }
        }
    }
    suspend fun getGoogleAuhResult(task: Task<GoogleSignInAccount>): Result<GoogleSignInAccount?> {
        return withContext(Dispatchers.IO) {
            try {
                val account = task.getResult(ApiException::class.java)
                Result.success(account)
            } catch (ex: ApiException) {
                Log.i("AuthViewModel", "Error ${ex.localizedMessage}")
                Result.failure<GoogleSignInAccount>(authException(ex))
            }
        }
    }
    suspend fun getDocumentResult(task: Task<DocumentSnapshot>): Result<DocumentSnapshot?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = task.await()
                if (result.exists()) {
                    Result.success(result)
                } else {
                    Result.success(null)
                }
            } catch (ex: Exception) {
                Result.failure(ex)
            }
        }
    }

    suspend fun getVoidResult(task: Task<Void>): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            val completionDeferred = CompletableDeferred<Result<Boolean>>()
            try {
                task.addOnSuccessListener {
                    completionDeferred.complete(Result.success(true))
                }
                    .addOnFailureListener { exception ->
                        completionDeferred.complete(Result.failure(exception))
                    }
            } catch (ex: Exception) {
                completionDeferred.complete(Result.failure(ex))
            }
            completionDeferred.await()
        }
    }

    suspend fun getDocumentReferenceResult(task: Task<DocumentReference>): Result<DocumentReference> {
        return withContext(Dispatchers.IO) {
            val completionDeferred = CompletableDeferred<Result<DocumentReference>>()
            task.addOnSuccessListener {
                completionDeferred.complete(Result.success(it))
            }
                .addOnFailureListener { exception ->
                    completionDeferred.complete(Result.failure(exception))
                }
            completionDeferred.await()
        }
    }

    suspend fun getDocumentReferenceBooleanResult(task: Task<DocumentReference>): Result<Boolean> {
        return withContext(Dispatchers.IO) {
            val completionDeferred = CompletableDeferred<Result<Boolean>>()
            task.addOnSuccessListener {
                completionDeferred.complete(Result.success(true))
            }
                .addOnFailureListener { exception ->
                    completionDeferred.complete(Result.failure(exception))
                }
            completionDeferred.await()
        }
    }

    suspend fun getQuerySnapshotResult(task: Task<QuerySnapshot>): Result<List<DocumentSnapshot>> {
        return withContext(Dispatchers.IO) {
            val completionDeferred = CompletableDeferred<Result<List<DocumentSnapshot>>>()
            task.addOnSuccessListener {
                completionDeferred.complete(Result.success(it.documents))
            }
                .addOnFailureListener {
                    completionDeferred.complete(Result.failure(it))
                }
            completionDeferred.await()
        }
    }
}