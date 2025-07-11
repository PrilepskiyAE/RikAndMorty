package com.prilepskiy.presentation.detailScreen.viewModel

import com.prilepskiy.mvi.MviBaseViewModel
import com.prilepskiy.mvi.Reducer
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailAction
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailIntent
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailReducer
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailReducer: DetailReducer,
) : MviBaseViewModel<DetailState, DetailAction, DetailIntent>() {
    override var reducer: Reducer<DetailAction, DetailState> = detailReducer

    override fun initState(): DetailState = DetailState()

    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.OnError -> onAction(DetailAction.OnError(intent.error))
            is DetailIntent.OnLoading -> onAction(DetailAction.OnLoading(intent.isLoading))
            is DetailIntent.GetUser -> {onAction(DetailAction.GetUser(intent.userId))}
        }
    }
}

