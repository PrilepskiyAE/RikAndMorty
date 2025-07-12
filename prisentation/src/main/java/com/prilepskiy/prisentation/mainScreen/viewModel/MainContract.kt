package com.prilepskiy.prisentation.mainScreen.viewModel

import androidx.paging.PagingData
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.domain.model.UiCharacnedModel
import com.prilepskiy.mvi.MviAction
import com.prilepskiy.mvi.MviIntent
import com.prilepskiy.mvi.MviState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


sealed class MainIntent : MviIntent {
    data class OnError(val error: String?) : MainIntent()
    data object GetCharacned : MainIntent()

    data class OnLoading(val isLoading: Boolean) : MainIntent()
    data class GetFilter(
        val name: String, val status: String, val type: String, val gender: String,
    ) : MainIntent()

}

sealed class MainAction : MviAction {
    data class GetCharacned(
        val characnedList: PagingData<UiCharacnedModel>,

    ) : MainAction()

    data class GetFilter(
        val name: String,
        val status: String,
        val type: String,
        val gender: String,
    ) : MainAction()

    data class OnError(val error: String?) : MainAction()
    data class OnLoading(val isLoading: Boolean) : MainAction()
}

data class MainState(
    val characnedList: PagingData<UiCharacnedModel> = PagingData.empty(),
    val name: String = EMPTY_STRING,
    val status: String = EMPTY_STRING,
    val type: String = EMPTY_STRING,
    val gender: String = EMPTY_STRING,
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : MviState