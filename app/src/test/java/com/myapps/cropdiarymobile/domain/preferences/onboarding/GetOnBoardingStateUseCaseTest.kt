package com.myapps.cropdiarymobile.domain.preferences.onboarding

import android.content.Context
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOnBoardingStateUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    @RelaxedMockK
    private lateinit var dialogViewModel: DialogViewModel

    @RelaxedMockK
    private lateinit var context: Context

    lateinit var getOnBoardingStateUseCase: GetOnBoardingStateUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getOnBoardingStateUseCase = GetOnBoardingStateUseCase(repository, dialogViewModel, context)
    }

    @Test
    fun `when the repository returns null then return false`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } returns null

        assert(!getOnBoardingStateUseCase())
        verify(exactly = 0) {
            dialogViewModel.showDialog(any(), any())
        }
    }

    @Test
    fun `when the repository returns true then return true`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } returns true

        //Then
        assert(getOnBoardingStateUseCase())
        verify(exactly = 0) {
            dialogViewModel.showDialog(any(), any())
        }
    }

    @Test
    fun `when the repository returns false then return false`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } returns false

        //Then
        assert(!getOnBoardingStateUseCase())
        verify(exactly = 0) {
            dialogViewModel.showDialog(any(), any())
        }
    }

    @Test
    fun `when the repository has an error then return false`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } throws Exception()

        //Then
        assert(!getOnBoardingStateUseCase())
        verify(exactly = 1) {
            dialogViewModel.showDialog(any(), any())
        }
    }
}