package com.myapps.cropdiarymobile.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.myapps.cropdiarymobile.core.util.PreferencesConstants.DATA_STORE_NAME
import com.myapps.cropdiarymobile.data.repository.PreferencesRepositoryImpl
import com.myapps.cropdiarymobile.data.source.preferences.AuthPreferences
import com.myapps.cropdiarymobile.data.source.preferences.AuthPreferencesImpl
import com.myapps.cropdiarymobile.data.source.preferences.OnBoardingPreferences
import com.myapps.cropdiarymobile.data.source.preferences.OnBoardingPreferencesImpl
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


private val Context.datastore by preferencesDataStore(DATA_STORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideDataStorePreferences(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.datastore
    }

    @Provides
    @Singleton
    fun provideOnBoardingPreferences(dataStore: DataStore<Preferences>): OnBoardingPreferences =
        OnBoardingPreferencesImpl(dataStore)

    @Provides
    @Singleton
    fun provideAuthPreferences(dataStore: DataStore<Preferences>): AuthPreferences = AuthPreferencesImpl(dataStore)

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        onBoardingPreferences: OnBoardingPreferences, authPreferences: AuthPreferences
    ): PreferencesRepository = PreferencesRepositoryImpl(onBoardingPreferences, authPreferences)
}