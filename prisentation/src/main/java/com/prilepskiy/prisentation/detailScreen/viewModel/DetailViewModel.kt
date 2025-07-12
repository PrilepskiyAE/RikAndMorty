package com.prilepskiy.prisentation.detailScreen.viewModel

import androidx.lifecycle.viewModelScope
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.usecase.GetChDetailUseCase
import com.prilepskiy.mvi.MviBaseViewModel
import com.prilepskiy.mvi.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailReducer: DetailReducer,
    private val getChDetailUseCase: GetChDetailUseCase
) : MviBaseViewModel<DetailState, DetailAction, DetailIntent>() {
    override var reducer: Reducer<DetailAction, DetailState> = detailReducer

    override fun initState(): DetailState = DetailState()

    override fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.OnError -> onAction(DetailAction.OnError(intent.error))
            is DetailIntent.OnLoading -> onAction(DetailAction.OnLoading(intent.isLoading))
            is DetailIntent.GetUser -> {
                getChDetailUseCase.invoke(intent.userId).subscribe(
                    viewModelScope,
                    onStart = {
                        onAction(DetailAction.OnLoading(true))
                    }, success = {
                        onAction(DetailAction.GetUser(it))
                    }, error = {
                        onAction(DetailAction.OnError(it.message))
                    }
                )

            }
        }
    }
}

