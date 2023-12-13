package com.myapps.cropdiarymobile.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideActivity(@ApplicationContext context: Context): Activity {
        if (context is Activity) {
            return context
        } else {
            throw IllegalArgumentException("Context is not an Activity")
        }
    }
}*/
