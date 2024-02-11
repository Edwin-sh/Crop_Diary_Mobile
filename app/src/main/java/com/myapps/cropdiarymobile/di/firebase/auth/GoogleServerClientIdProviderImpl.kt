package com.myapps.cropdiarymobile.di.firebase.auth

import com.example.cropdiary.di.auth.GoogleServerClientIdProvider

class GoogleServerClientIdProviderImpl : GoogleServerClientIdProvider {
    override fun getGoogleServerClientId(): String {
        return "318308638225-4t7bqu1b62v6b5sirb0mgcufk822c52j.apps.googleusercontent.com"
    }
}