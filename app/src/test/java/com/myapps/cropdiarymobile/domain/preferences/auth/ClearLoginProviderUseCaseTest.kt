package com.myapps.cropdiarymobile.domain.preferences.auth

import android.content.Context
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class ClearLoginProviderUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    @RelaxedMockK
    private lateinit var dialogViewModel: DialogViewModel

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var clearLoginProviderUseCase: ClearLoginProviderUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        clearLoginProviderUseCase = ClearLoginProviderUseCase(repository, dialogViewModel, context)
    }

    @After
    fun onAfter() {
        dialogViewModel.hideDialog()
    }

    @Test
    fun `when the login provider is cleared then return true`() = runBlocking {
        // Given
        coEvery { repository.clearSignInProvider() } returns Unit

        // When
        val result = clearLoginProviderUseCase()

        // Then
        assertTrue(result)
        coVerify(exactly = 1) { repository.clearSignInProvider() }
        verify(exactly = 0) { dialogViewModel.showDialog(any(), any()) }
    }

    @Test
    fun `when an error occurs while clearing the login provider then return false`() = runBlocking {
        // Given
        coEvery { repository.clearSignInProvider() } throws Exception()

        // When
        val result = clearLoginProviderUseCase()

        // Then
        assertTrue(!result)
        coVerify(exactly = 1) { repository.clearSignInProvider() }
        verify(exactly = 1) { dialogViewModel.showDialog(any(), any()) }
    }
}