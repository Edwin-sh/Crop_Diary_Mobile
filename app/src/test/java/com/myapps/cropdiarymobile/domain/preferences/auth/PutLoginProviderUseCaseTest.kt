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

class PutLoginProviderUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    @RelaxedMockK
    private lateinit var dialogViewModel: DialogViewModel

    @RelaxedMockK
    private lateinit var context: Context

    lateinit var putLoginProviderUseCase: PutLoginProviderUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        putLoginProviderUseCase = PutLoginProviderUseCase(repository, dialogViewModel, context)
    }

    @After
    fun onAfter() {
        dialogViewModel.hideDialog()
    }

    @Test
    fun `when the singing provider is set then return true`() = runBlocking {
        //Given
        coEvery { repository.putSignInProvider(any()) } returns Unit

        //Then
        assert(putLoginProviderUseCase(ProviderType.GOOGLE))
        coVerify { repository.putSignInProvider(any()) }
        verify(exactly = 0) { dialogViewModel.showDialog(any(), any()) }
    }

    @Test
    fun `when an error occurs while setting the signing provider then return false`() = runBlocking {
        //Given
        coEvery { repository.putSignInProvider(any()) } throws Exception()

        //Then
        assert(!putLoginProviderUseCase(ProviderType.GOOGLE))
        coVerify { repository.putSignInProvider(any()) }
        verify { dialogViewModel.showDialog(any(), any()) }
    }
}