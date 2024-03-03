package com.myapps.cropdiarymobile.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.firebase.auth.FirebaseAuth
import com.myapps.cropdiarymobile.R
import com.myapps.cropdiarymobile.core.RequestCodes
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class ConnectionChecker @Inject constructor(
    @ApplicationContext private val context: Context,
    private val firebaseAuth: FirebaseAuth
) {

    val notInternetConnectionMessage =
        context.getString(R.string.you_do_not_have_an_internet_connection)
    val notInternetReachableMessage =
        context.getString(R.string.your_internet_connection_is_unstable_please_try_again)
    val exceptionWhileVerifyingInternetConnectionMessage =
        context.getString(R.string.an_error_occurred_while_verifying_the_internet_connection)
    val notFirebaseConnectionReachable =
        context.getString(R.string.the_connection_to_the_firebase_is_unstable_please_try_again)
    val exceptionWhileVerifyingFirebaseConnectionMessage =
        context.getString(R.string.an_error_occurred_while_verifying_the_firebase_connection_please_try_again)

    fun isInternetAvailable(): Boolean {
        return try {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val network = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } catch (e: Exception) {
            false
        }
    }

    fun isInternetReachable(): Result<Boolean> {
        return try {
            val httpConnection: HttpURLConnection = URL("https://clients3.google.com/generate_204")
                .openConnection() as HttpURLConnection
            httpConnection.setRequestProperty("User-Agent", "Android")
            httpConnection.setRequestProperty("Connection", "close")
            httpConnection.connectTimeout = 1000
            httpConnection.connect()
            Result.success(httpConnection.responseCode == RequestCodes.CONNECTION_RESPONSE)
        } catch (e: Exception) {
            return Result.failure(
                Exception(
                    "${exceptionWhileVerifyingInternetConnectionMessage}: ${e.message}",
                    e
                )
            )
        }
    }

    fun isFirebaseAvailable(): Result<Boolean> {
        return try {
            firebaseAuth.signInAnonymously().addOnCompleteListener {
                Result.success(it.isSuccessful)
            }
            Result.success(false)
        } catch (e: Exception) {
            return Result.failure(
                Exception(
                    "${exceptionWhileVerifyingFirebaseConnectionMessage}: ${e.message}", e
                )
            )
        }
    }
}