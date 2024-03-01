package com.myapps.cropdiarymobile.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.myapps.cropdiarymobile.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DialogViewModelTest {
    @RelaxedMockK
    private lateinit var context: Context

    private var dispatcher = TestCoroutineDispatcher()

    private lateinit var dialogViewModel: DialogViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val ruleCoroutine = CoroutineTestRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        dialogViewModel = DialogViewModel(context, dispatcher)
    }

   @After
    fun onAfter() {
        dialogViewModel.hideDialog()
    }

    @Test
    fun `when the view model is created then the dialog state is set default values`() {
        assert(!dialogViewModel.state.isShowing)
        assert(dialogViewModel.state.title.isEmpty())
        assert(dialogViewModel.state.message.isEmpty())
        assert(dialogViewModel.state.positiveButtonText.isEmpty())
        assert(dialogViewModel.state.positiveButtonAction.invoke() == Unit)
    }

    @Test
    fun `when the show dialog fun is called then the showing state is true`() {
        //When
        showGenericDialog()
        ///Then
        dispatcher.scheduler.advanceUntilIdle()
        assert(dialogViewModel.state.isShowing)
    }

    @Test
    fun `when the show dialog fun is called with custom values then the dialog state is set`() {
        //When
        showGenericDialog()
        dispatcher.scheduler.advanceUntilIdle()

        ///Then
        assert(dialogViewModel.state.title == "Test title")
        assert(dialogViewModel.state.message == "Test message")
        assert(dialogViewModel.state.positiveButtonText == "Test positive button")
        assert(dialogViewModel.state.positiveButtonAction.invoke() == println("Test"))
        assert(dialogViewModel.state.negativeButtonText == "Test negative button")
        assert(dialogViewModel.state.negativeButtonAction.invoke() == println("Test"))
    }


    @Test
    fun `when the clear dialog fun is called then the dialog state is set default values`() {
        //When
        showGenericDialog()
        dialogViewModel.clearDialogData()

        //Then
        assert(!dialogViewModel.state.isShowing)
        assert(dialogViewModel.state.title.isEmpty())
        assert(dialogViewModel.state.message.isEmpty())
        assert(dialogViewModel.state.positiveButtonText.isEmpty())
        assert(dialogViewModel.state.positiveButtonAction.invoke() == Unit)
    }

    @Test
    fun `when the hide dialog fun is called then the showing state is false and the clear dialog fun is called`() {
        //When
        showGenericDialog()
        dialogViewModel.hideDialog()

        //Then
        assert(!dialogViewModel.state.isShowing)
    }


    private fun showGenericDialog() {
        dialogViewModel.showDialog(
            title = "Test title",
            message = "Test message",
            positiveButtonText = "Test positive button",
            positiveButtonAction = { println("Test") },
            negativeButtonText = "Test negative button",
            negativeButtonAction = { println("Test") }
        )
    }
}