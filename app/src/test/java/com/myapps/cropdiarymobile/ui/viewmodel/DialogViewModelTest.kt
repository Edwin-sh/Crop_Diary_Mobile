package com.myapps.cropdiarymobile.ui.viewmodel

import android.content.Context
import com.myapps.cropdiarymobile.data.state.DialogStateData
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test

class DialogViewModelTest {
    @RelaxedMockK
    private lateinit var context: Context

    @RelaxedMockK
    private lateinit var dialogStateData: DialogStateData

    @RelaxedMockK
    private lateinit var dispatcher: TestCoroutineDispatcher

    private lateinit var dialogViewModel: DialogViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        dialogViewModel = DialogViewModel(context, dialogStateData, dispatcher)
    }

    @Test
    fun `when the view model is created then the dialog state is set default values`() {
        assert(!dialogViewModel.state.isShowing)
        assert(dialogViewModel.state.title.isEmpty())
        assert(dialogViewModel.state.message.isEmpty())
        assert(dialogViewModel.state.positiveButtonText.isEmpty())
        //assert(function=={})
    }
}