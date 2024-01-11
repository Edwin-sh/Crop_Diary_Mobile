package com.myapps.cropdiarymobile.di.auth

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object CoroutinesModule {
    @Provides
    @ViewModelScoped
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}