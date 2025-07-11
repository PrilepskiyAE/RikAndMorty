package com.prilepskiy.prisentation.mainScreen.viewModel

import com.prilepskiy.mvi.MviAction
import com.prilepskiy.mvi.MviIntent
import com.prilepskiy.mvi.MviState


sealed class MainIntent : MviIntent {
    data class OnClick(val userId:Int) : MainIntent()
    data class OnError(val error: String?) : MainIntent()
    data object OnClear : MainIntent()
    data class OnLoading(val isLoading: Boolean) : MainIntent()
}

sealed class MainAction : MviAction {
    data class GetUser(val users: List<String>) : MainAction()
    data class OnError(val error: String?) : MainAction()
    data class OnLoading(val isLoading: Boolean) : MainAction()
}

data class MainState(
    val users:List<String> = listOf(),
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState