package com.prilepskiy.prisentation.mainScreen.ui.screen


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import com.prilepskiy.prisentation.mainScreen.viewModel.MainViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.presentation.uiComponent.TextComponent
import com.prilepskiy.prisentation.utils.items
import kotlinx.coroutines.flow.flow


@Composable
fun MainScreen(goToUser: (Int) -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.viewState
    val openDialog = remember { mutableStateOf(false) }

    val lazyListState = rememberLazyListState()

    val lazyPagingItems = remember(state.characnedList) {
        flow {
            emit(state.characnedList)
        }
    }.collectAsLazyPagingItems()

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id},
            contentType = lazyPagingItems.itemContentType { "contentType" }
        ) { index ->
            val item = lazyPagingItems[index]
            TextComponent(modifier = Modifier, item?.name ?: EMPTY_STRING)
        }
    }

}
