package com.myapps.cropdiarymobile.core.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
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
    fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
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
            return Result.success(httpConnection.responseCode == RequestCodes.CONNECTION_RESPONSE)
        } catch (e: Exception) {
            return Result.failure(
                Exception(
                    "${context.getString(R.string.an_error_occurred_while_verifying_the_internet_connection)} ${e.message}",
                    e
                )
            )
        }
    }

    fun isFirebaseAvailable(): Result<Boolean> {
        return try {
            firebaseAuth.signInAnonymously().addOnCompleteListener {
                if (it.isSuccessful) {
                    Result.success(true)
                }
            }
            Result.success(false)
        } catch (e: Exception) {
            Log.e("ConnectionChecker", "Firebase connection error: ${e.message}")
            return Result.failure(Exception("Firebase connection error: ${e.message}", e))
        }
    }
}