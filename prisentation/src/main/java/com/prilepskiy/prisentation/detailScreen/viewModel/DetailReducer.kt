package com.prilepskiy.prisentation.detailScreen.viewModel

import com.prilepskiy.mvi.Reducer
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailAction
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailState
import javax.inject.Inject

class DetailReducer @Inject constructor() : Reducer<DetailAction, DetailState> {
    override fun reduce(action: DetailAction, state: DetailState): DetailState = when (action) {
        is DetailAction.OnLoading-> state.copy(isLoading = action.isLoading)
        is DetailAction.OnError -> state.copy(error = action.error, isLoading = false)
        is DetailAction.GetUser -> state.copy(userModel = action.userModel ,isLoading = false)
    }
}