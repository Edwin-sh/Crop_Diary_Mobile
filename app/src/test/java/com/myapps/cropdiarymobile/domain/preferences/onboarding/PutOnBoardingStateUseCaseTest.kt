package com.myapps.cropdiarymobile.domain.preferences.onboarding

import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PutOnBoardingStateUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    private lateinit var putOnBoardingStateUseCase: PutOnBoardingStateUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        putOnBoardingStateUseCase = PutOnBoardingStateUseCase(repository)
    }

    @Test
    fun `when the repository correctly executes the method`() = runBlocking {
        //Given
        coEvery { repository.putOnBoardingState(any()) } returns Unit

        //When
        val result = putOnBoardingStateUseCase(true)

        //Then
        coVerify { repository.putOnBoardingState(true) }
        assert(result)
    }

    @Test
    fun `when the repository throws an exception`() = runBlocking {
        //Given
        coEvery { repository.putOnBoardingState(any()) } throws Exception()

        //When
        val result = putOnBoardingStateUseCase(true)

        //Then
        coVerify { repository.putOnBoardingState(true) }
        assert(!result)


    }



}