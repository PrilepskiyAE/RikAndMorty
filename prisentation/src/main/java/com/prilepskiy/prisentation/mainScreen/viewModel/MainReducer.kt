package com.prilepskiy.prisentation.mainScreen.viewModel

import com.prilepskiy.mvi.Reducer
import javax.inject.Inject

class MainReducer @Inject constructor() : Reducer<MainAction, MainState> {
    override fun reduce(action: MainAction, state: MainState): MainState = when (action) {
        is MainAction.OnLoading -> state.copy(isLoading = action.isLoading)
        is MainAction.OnError -> state.copy(error = action.error, isLoading = false)
        is MainAction.GetCharacned -> state.copy(characnedList = action.characnedList, isLoading = false)
        is MainAction.GetFilter -> state.copy(
            name = action.name,
            gender = action.gender
        )
    }
}