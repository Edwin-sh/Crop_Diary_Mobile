package com.myapps.cropdiarymobile.di

import android.content.Context
import com.myapps.cropdiarymobile.data.state.DialogStateData
import com.myapps.cropdiarymobile.di.coroutines.DefaultDispatcher
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DialogModule {
    @Singleton
    @Provides
    fun provideDialogStateData(): DialogStateData {
        return DialogStateData()
    }

    @Singleton
    @Provides
    fun provideDialogViewModel(
        @ApplicationContext context: Context,
        dialogStateData: DialogStateData,
        @DefaultDispatcher dispatcher: CoroutineDispatcher
    ): DialogViewModel {
        return DialogViewModel(context, dialogStateData, dispatcher)
    }
}