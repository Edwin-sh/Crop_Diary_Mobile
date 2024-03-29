package com.myapps.cropdiarymobile.di.firebase.auth

import android.content.Context
import com.example.cropdiary.di.auth.GoogleServerClientIdProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun provideGoogleSignInClient(@ApplicationContext context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, GoogleSignInOptions.DEFAULT_SIGN_IN)
    }

    @Singleton
    @Provides
    fun provideGoogleSignInOptions(googleServerClientIdProvider: GoogleServerClientIdProvider):GoogleSignInOptions{
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(googleServerClientIdProvider.getGoogleServerClientId())
            .requestEmail()
            .build()
    }
    @Singleton
    @Provides
    fun provideGoogleServerClientIdProvider(): GoogleServerClientIdProvider {
        return GoogleServerClientIdProviderImpl()
    }
}