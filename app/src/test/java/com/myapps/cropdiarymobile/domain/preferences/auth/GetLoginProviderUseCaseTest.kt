package com.myapps.cropdiarymobile.domain.preferences.auth

import android.content.Context
import com.myapps.cropdiarymobile.data.auth.ProviderType
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetLoginProviderUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    @RelaxedMockK
    private lateinit var dialogViewModel: DialogViewModel

    @RelaxedMockK
    private lateinit var context: Context

    lateinit var getLoginProviderUseCase: GetLoginProviderUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getLoginProviderUseCase = GetLoginProviderUseCase(repository, dialogViewModel, context)
    }

    @After
    fun onAfter() {
        dialogViewModel.hideDialog()
    }

    @Test
    fun `when the login provider is get successfully the return the provider type`() =
        runBlocking {
            //Given
            coEvery { repository.getSignInProvider() } returns ProviderType.BASIC

            //When
            val result = getLoginProviderUseCase()
            //Then
            assert(result == ProviderType.BASIC)
            coVerify(exactly = 1) { repository.getSignInProvider() }
            verify(exactly = 0) { dialogViewModel.showDialog(any(), any()) }
        }

    @Test
    fun `when an error occurs while getting the login provider then return null`() =
        runBlocking {
            //Given
            coEvery { repository.getSignInProvider() } throws Exception()

            //When
            val result = getLoginProviderUseCase()
            //Then
            assert(result == null)
            coVerify(exactly = 1) { repository.getSignInProvider() }
            verify(exactly = 1) { dialogViewModel.showDialog(any(), any()) }
        }
}