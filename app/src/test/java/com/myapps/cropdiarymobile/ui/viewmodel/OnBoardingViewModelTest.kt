package com.myapps.cropdiarymobile.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.myapps.cropdiarymobile.data.state.OnBoardingState
import com.myapps.cropdiarymobile.domain.preferences.onboarding.GetOnBoardingStateUseCase
import com.myapps.cropdiarymobile.domain.preferences.onboarding.PutOnBoardingStateUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OnBoardingViewModelTest {
    @RelaxedMockK
    private lateinit var getOnBoardingStateUseCase: GetOnBoardingStateUseCase

    @RelaxedMockK
    private lateinit var putOnBoardingStateUseCase: PutOnBoardingStateUseCase

    private lateinit var onBoardingViewModel: OnBoardingViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        onBoardingViewModel =
            OnBoardingViewModel(
                getOnBoardingStateUseCase,
                putOnBoardingStateUseCase,
                testDispatcher
            )

    }

    @After
    fun onAfter() {
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when the view model is created at the first time then get the onBoarding state`() =
        runTest {
            // Given
            val initialOnBoardingState = OnBoardingState(isLoading = true)
            coEvery { getOnBoardingStateUseCase() } returns true

            // Then
            assertEquals(initialOnBoardingState, onBoardingViewModel.state)
            testDispatcher.scheduler.advanceUntilIdle()
            coVerify(exactly = 1) { getOnBoardingStateUseCase() } // Verifica que se haya llamado al use case
            assertEquals(
                OnBoardingState(isComplete = true, isLoading = false),
                onBoardingViewModel.state
            )
        }
}