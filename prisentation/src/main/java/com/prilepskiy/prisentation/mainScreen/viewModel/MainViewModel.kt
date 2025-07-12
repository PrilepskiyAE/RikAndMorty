package com.prilepskiy.prisentation.mainScreen.viewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.prilepskiy.common.subscribe
import com.prilepskiy.domain.usecase.GetFilterChUseCase
import com.prilepskiy.mvi.MviBaseViewModel
import com.prilepskiy.mvi.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainReducer: MainReducer,
    private val getFilterChUseCase: GetFilterChUseCase
) : MviBaseViewModel<MainState, MainAction, MainIntent>() {
    init {
        getCharacned()
    }
    override var reducer: Reducer<MainAction, MainState> = mainReducer

    override fun initState(): MainState = MainState()

    override fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.OnError -> onAction(MainAction.OnError(intent.error))
            is MainIntent.OnLoading -> onAction(MainAction.OnLoading(intent.isLoading))
            is MainIntent.GetCharacned -> getCharacned()


            is MainIntent.GetFilter -> onAction(
                MainAction.GetFilter(
                    intent.name,
                    intent.status,
                    intent.type,
                    intent.gender
                )
            )
        }
    }

    private fun getCharacned() {
        getFilterChUseCase.invoke(
            viewState.name,
            viewState.status,
            viewState.type,
            viewState.gender
        ).cachedIn(viewModelScope).subscribe(
            viewModelScope,
            onStart = {
                MainAction.OnLoading(true)
            },
            success = {
                onAction(MainAction.GetCharacned(it))
            },
            error = {
                onAction(MainAction.OnError(it.message))
            }
        )
    }
}