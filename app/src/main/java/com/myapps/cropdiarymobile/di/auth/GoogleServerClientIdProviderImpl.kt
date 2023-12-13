package com.example.cropdiary.di.auth

import androidx.core.content.res.TypedArrayUtils.getString
import com.myapps.cropdiarymobile.R

class GoogleServerClientIdProviderImpl : GoogleServerClientIdProvider {
    override fun getGoogleServerClientId(): String {
        return "318308638225-4t7bqu1b62v6b5sirb0mgcufk822c52j.apps.googleusercontent.com"
    }
}