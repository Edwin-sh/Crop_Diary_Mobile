package com.myapps.cropdiarymobile.domain.preferences.onboarding

import com.myapps.cropdiarymobile.domain.preferences.PreferencesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOnBoardingStateUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: PreferencesRepository

    lateinit var getOnBoardingStateUseCase: GetOnBoardingStateUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getOnBoardingStateUseCase = GetOnBoardingStateUseCase(repository)
    }

    @Test
    fun `when the repository returns null then return false`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } returns null

        //When
        val result = getOnBoardingStateUseCase()

        //Then
        assert(!result)
    }

    @Test
    fun `when the repository returns true then return true`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } returns true

        //Then
        assert(getOnBoardingStateUseCase())
    }

    @Test
    fun `when the repository returns false then return false`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } returns false

        //When
        val result = getOnBoardingStateUseCase()

        //Then
        assert(!result)
    }

    @Test
    fun `when the repository has an error then return false`() = runBlocking {
        //Given
        coEvery { repository.getOnBoardingState() } throws Exception()

        //When
        val result = getOnBoardingStateUseCase()

        //Then
        assert(!result)
    }
}