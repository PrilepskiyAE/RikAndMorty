package com.prilepskiy.prisentation.detailScreen.viewModel

import com.prilepskiy.domain.model.UiCharacnedModel
import com.prilepskiy.mvi.MviAction
import com.prilepskiy.mvi.MviIntent
import com.prilepskiy.mvi.MviState

sealed class DetailIntent : MviIntent {
    data class GetUser(val userId:Int) : DetailIntent()
    data class OnError(val error: String?) : DetailIntent()
    data class OnLoading(val isLoading: Boolean) : DetailIntent()
}

sealed class DetailAction : MviAction {
    data class GetUser(val userModel: UiCharacnedModel) : DetailAction()
    data class OnError(val error: String?) : DetailAction()
    data class OnLoading(val isLoading: Boolean) : DetailAction()
}

data class DetailState(
    val userModel: UiCharacnedModel?=null,
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState