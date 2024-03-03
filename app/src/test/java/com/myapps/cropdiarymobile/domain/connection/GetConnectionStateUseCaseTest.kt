package com.myapps.cropdiarymobile.domain.connection

import com.myapps.cropdiarymobile.core.util.ConnectionChecker
import com.myapps.cropdiarymobile.ui.viewmodel.DialogViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetConnectionStateUseCaseTest {
    @RelaxedMockK
    private lateinit var viewModel: DialogViewModel

    @RelaxedMockK
    lateinit var connectionChecker: ConnectionChecker

    lateinit var getConnectionStateUseCase: GetConnectionStateUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getConnectionStateUseCase = GetConnectionStateUseCase(viewModel, connectionChecker)
    }

    @After
    fun onAfter() {
        viewModel.hideDialog()
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
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when the connection is not available then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns false

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) {
            viewModel.showDialog(
                title = viewModel.error,
                message = connectionChecker.notInternetConnectionMessage
            )
        }
        verify(exactly = 0) { connectionChecker.isInternetReachable() }
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
        verify(exactly = 1) {
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }


    @Test
    fun `when the connection is available but the firebase is not reachable then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(true)
        every { connectionChecker.isFirebaseAvailable() } returns Result.success(false)

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { connectionChecker.isFirebaseAvailable() }
        verify(exactly = 1) {
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
    }

    @Test
    fun `when an error occurs into verifying the internet reachability process then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.failure(Exception())

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) {
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when an error occurs into verifying the firebase connection process then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(true)
        every { connectionChecker.isFirebaseAvailable() } returns Result.failure(Exception())

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { connectionChecker.isFirebaseAvailable() }
        verify(exactly = 1) {
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
    }

    @Test
    fun `when an error occurs while verifying the internet connection then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } throws Exception()

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1){
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
        verify(exactly = 0) { connectionChecker.isInternetReachable() }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when an error occurs while verifying the internet connection reachability then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } throws Exception()

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) {
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
        verify(exactly = 0) { connectionChecker.isFirebaseAvailable() }
    }

    @Test
    fun `when an error occurs while verifying the firebase connection then return false`() {
        //Given
        every { connectionChecker.isInternetAvailable() } returns true
        every { connectionChecker.isInternetReachable() } returns Result.success(true)
        every { connectionChecker.isFirebaseAvailable() } throws Exception()

        //When
        val result = getConnectionStateUseCase()

        //Then
        assert(!result)
        verify(exactly = 1) { connectionChecker.isInternetAvailable() }
        verify(exactly = 1) { connectionChecker.isInternetReachable() }
        verify(exactly = 1) { connectionChecker.isFirebaseAvailable() }
        verify(exactly = 1) {
            viewModel.showDialog(
                title = any(), message = any()
            )
        }
    }
}