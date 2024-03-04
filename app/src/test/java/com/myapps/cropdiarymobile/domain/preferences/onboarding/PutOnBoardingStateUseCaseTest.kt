package com.myapps.cropdiarymobile.domain.preferences.onboarding

import android.content.Context
import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PutOnBoardingStateUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    @RelaxedMockK
    private lateinit var dialogViewModel: DialogViewModel

    @RelaxedMockK
    private lateinit var context: Context

    private lateinit var putOnBoardingStateUseCase: PutOnBoardingStateUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        putOnBoardingStateUseCase = PutOnBoardingStateUseCase(repository, dialogViewModel, context)
    }

    @Test
    fun `when the repository correctly executes the method`() = runBlocking {
        //Given
        coEvery { repository.putOnBoardingState(any()) } returns Unit

        //When
        val result = putOnBoardingStateUseCase(true)

        //Then
        assert(result)
        coVerify(exactly = 1) { repository.putOnBoardingState(true) }
        verify(exactly = 0){ dialogViewModel.showDialog(any(), any())}

    }

    @Test
    fun `when the repository throws an exception`() = runBlocking {
        //Given
        coEvery { repository.putOnBoardingState(any()) } throws Exception()

        //When
        val result = putOnBoardingStateUseCase(true)

        //Then
        assert(!result)
        coVerify { repository.putOnBoardingState(true) }
        verify(exactly = 1){ dialogViewModel.showDialog(any(), any())}
    }



}