package com.myapps.cropdiarymobile.domain.connection

import android.content.Context
import com.myapps.cropdiarymobile.core.util.ConnectionChecker
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class GetConnectionStateUseCaseTest {
    @RelaxedMockK
    private lateinit var viewModel: DialogViewModel

    @RelaxedMockK
    private lateinit var connectionChecker: ConnectionChecker

    @RelaxedMockK
    private lateinit var context: Context

    lateinit var getConnectionStateUseCase: GetConnectionStateUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getConnectionStateUseCase = GetConnectionStateUseCase(viewModel, connectionChecker, context)
    }

    @Test
    fun `when the connection is available then return true`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(true)
        every { connectionChecker.isFirebaseAvailable() } returns Result.success(true)

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(result)
        verify { connectionChecker.isInternetAvailable() }
        verify { connectionChecker.isInternetReachable() }
        verify { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when the connection is not available then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns false

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1){ connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
        verify(exactly = 0) { connectionChecker.isInternetReachable()}
    }

    @Test
    fun `when the connection is available but the internet is not reachable then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.failure(Exception("Internet not reachable"))

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when the connection is available but the internet is unstable then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(false)

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when the connection is available but the firebase is not reachable then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(true)
        every { connectionChecker.isFirebaseAvailable() } returns Result.failure(Exception("Firebase not reachable"))

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { connectionChecker.isFirebaseAvailable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
    }


    @Test
    fun `when an error occurs while verifying the internet connection then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } throws Exception("An error occurred while verifying the internet connection")

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
        verify(exactly = 0) { connectionChecker.isInternetReachable() }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when an error occurs while verifying the internet connection reachability then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } throws Exception("An error occurred while verifying the internet connection reachability")

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when an error occurs while verifying the firebase connection then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(true)
        every { connectionChecker.isFirebaseAvailable() } throws Exception("An error occurred while verifying the firebase connection")

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { connectionChecker.isFirebaseAvailable() }
        verify(exactly = 1) { viewModel.showErrorDialog(any()) }
    }
}