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
    fun `when the view model is created at the first time then the onBoarding state is loading`() =
        runTest {
            // Given
            val initialOnBoardingState = OnBoardingState(isLoading = true)
            // Then
            assertEquals(initialOnBoardingState, onBoardingViewModel.state)
            testDispatcher.scheduler.advanceUntilIdle()
        }

    @Test
    fun `when the view model is created at the first time then the get onBoarding state is executed`() =
        runTest {
            testDispatcher.scheduler.advanceUntilIdle()
            coVerify(exactly = 1) { getOnBoardingStateUseCase() }
        }

    @Test
    fun `when the get on boarding state use case is executed then the view model state is updated`() =
        runTest {
            // Given
            coEvery { getOnBoardingStateUseCase() } returns true
            testDispatcher.scheduler.advanceUntilIdle()

            // Then
            assertEquals(
                OnBoardingState(isComplete = true, isLoading = false),
                onBoardingViewModel.state
            )
        }

    @Test
    fun `when the put on boarding state use case is executed then the view model state is updated`() =
        runTest {
            // Given
            coEvery { putOnBoardingStateUseCase(any()) } returns true
            testDispatcher.scheduler.advanceUntilIdle()

            // Then
            assertEquals(
                OnBoardingState(isLoading = false),
                onBoardingViewModel.state
            )
        }

}