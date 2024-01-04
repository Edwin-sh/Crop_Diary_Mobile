package com.myapps.cropdiarymobile.core.util

import android.app.Activity
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.widget.EditText
import org.apache.commons.validator.routines.EmailValidator

object Utilities {
    private val patron = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+\$")

    fun networkConnection(context: Context): Boolean { //Internet Access Verification
        val con = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = con.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    fun isAlpha(value: Pair<EditText, String>, activity: Activity): Boolean {
        return patron.matches(value.first.text.toString())
    }

    fun isAlpha(list: List<Pair<EditText, String>>, activity: Activity): Boolean {
        for (value in list) {
            if (!patron.matches(value.first.text.toString())) {
                return false
            }
        }
        return true
    }

    fun isValid(email: EditText, activity: Activity): Boolean {
        if (!validateEmail(email.text.toString())) {
            return false
        }
        return true
    }

    /*fun isValid(email: EditText, password: EditText, activity: Activity): Boolean {
        if (!validateEmail(email.text.toString())) {
            return false
        }
        return true
    }*/

    fun validateEmail(email: String): Boolean {
        return EmailValidator.getInstance().isValid(email)
    }

    fun validatePassword(password: String): Boolean {
        return RegularExpressions.passwordRegex.matches(password)
    }

    fun noEmpty(list: List<Pair<EditText, String>>, activity: Activity): Boolean {
        for (value in list) {
            if (value.first.text.isEmpty()) {
                return false
            }
        }
        return true
    }

}