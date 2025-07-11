package com.prilepskiy.prisentation.mainScreen.viewModel

import com.prilepskiy.mvi.Reducer
import com.prilepskiy.prisentation.mainScreen.viewModel.MainAction
import com.prilepskiy.prisentation.mainScreen.viewModel.MainState
import javax.inject.Inject

class MainReducer @Inject constructor() : Reducer<MainAction, MainState> {
    override fun reduce(action: MainAction, state: MainState): MainState = when (action) {
        is MainAction.OnLoading-> state.copy(isLoading = action.isLoading)
        is MainAction.OnError -> state.copy(error = action.error, isLoading = false)
        is MainAction.GetUser-> state.copy(users = action.users, isLoading = false)
    }
}