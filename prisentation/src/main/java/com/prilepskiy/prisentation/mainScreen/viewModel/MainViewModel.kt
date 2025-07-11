package com.prilepskiy.prisentation.mainScreen.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.subscribe

import com.prilepskiy.mvi.MviBaseViewModel
import com.prilepskiy.mvi.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainReducer: MainReducer,
    ) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    override var reducer: Reducer<MainAction, MainState> = mainReducer

    override fun initState(): MainState = MainState()

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnError -> onAction(MainAction.OnError(intent.error))
            is MainIntent.OnLoading -> onAction(MainAction.OnLoading(intent.isLoading))
            is MainIntent.OnClick -> {}
            is MainIntent.OnClear -> {}
        }
    }


}