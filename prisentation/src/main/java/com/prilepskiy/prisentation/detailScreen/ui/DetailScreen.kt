package com.prilepskiy.presentation.detailScreen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.presentation.detailScreen.viewModel.DetailViewModel
import com.prilepskiy.presentation.uiComponent.ErrorMessageComponent
import com.prilepskiy.presentation.uiComponent.TextComponent
import com.prilepskiy.prisentation.detailScreen.viewModel.DetailIntent
import com.prilepskiy.prisentation.uiComponent.LoadingComponent


@Composable
fun DetailScreen(user: Int, viewModel: DetailViewModel = hiltViewModel(), onPopBack: () -> Unit) {
    val state = viewModel.viewState

    LaunchedEffect(user) {
        viewModel.onIntent(DetailIntent.GetUser(user))
    }

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
            onPopBack.invoke()
        }
    } else {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            IconButton(
                onClick = onPopBack, modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(horizontal = 10.dp, vertical = 30.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = EMPTY_STRING
                )
            }

            TextComponent(Modifier.fillMaxWidth(), "RIK AND MORTY SCREEN DETAIL $user")

        }

    }
}
