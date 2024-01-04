package com.myapps.cropdiarymobile.di

import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDialogViewModel(): DialogViewModel {
        return DialogViewModel()
    }
}
