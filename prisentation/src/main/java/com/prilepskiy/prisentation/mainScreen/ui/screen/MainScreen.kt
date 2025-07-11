package com.prilepskiy.prisentation.mainScreen.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.prilepskiy.presentation.uiComponent.ErrorMessageComponent
import com.prilepskiy.presentation.uiComponent.TextComponent
import com.prilepskiy.prisentation.mainScreen.viewModel.MainIntent
import com.prilepskiy.prisentation.mainScreen.viewModel.MainViewModel
import com.prilepskiy.prisentation.uiComponent.AlertDialogComponent
import com.prilepskiy.prisentation.uiComponent.LoadingComponent


@Composable
fun MainScreen(goToUser: (Int) -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.viewState
    val openDialog = remember { mutableStateOf(false) }

    if (state.isLoading) {
        LoadingComponent()
    } else if (!state.error.isNullOrEmpty()) {
        ErrorMessageComponent(textError = state.error) {
            viewModel.onIntent(MainIntent.OnClear)
        }
    } else {

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column {
                TextComponent(
                    Modifier.padding(18.dp)
                        .clickable { goToUser.invoke(99) },
                    "RIK AND MORTY MAIN SCREEN NAVIGATE"
                )

            }
            FloatingActionButton(modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp), onClick = { openDialog.value = true}) {
                Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Фильтр")
            }

        }
    }
    if (openDialog.value) {
        AlertDialogComponent(openDialog)
    }

}



