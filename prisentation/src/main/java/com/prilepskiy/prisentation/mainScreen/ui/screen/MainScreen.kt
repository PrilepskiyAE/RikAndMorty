package com.prilepskiy.prisentation.mainScreen.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.prilepskiy.common.EMPTY_STRING
import com.prilepskiy.prisentation.mainScreen.viewModel.MainIntent
import com.prilepskiy.prisentation.mainScreen.viewModel.MainViewModel
import com.prilepskiy.prisentation.uiComponent.CharacnedComponent
import com.prilepskiy.prisentation.uiComponent.EmptyStateComponent
import com.prilepskiy.prisentation.uiComponent.FullScreenDialogFilter
import com.prilepskiy.prisentation.uiComponent.LoadingComponent
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(goToUser: (Int) -> Unit, viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.viewState
    val openDialogFilter = remember { mutableStateOf(false) }
    var isRefreshing = remember { mutableStateOf(false) }
    val qery = remember { mutableStateOf(EMPTY_STRING) }
    val gender = remember { mutableStateOf("All") }
    val status = remember { mutableStateOf("All") }
    val stateRefreshState = rememberPullToRefreshState()
    val coroutineScope = rememberCoroutineScope()

    val lazyPagingItems = remember(state.characnedList) {
        flow {
            emit(state.characnedList)
        }
    }.collectAsLazyPagingItems()
    if (lazyPagingItems.itemCount == 0 && lazyPagingItems.loadState.refresh !is LoadState.Loading) {
        EmptyStateComponent()
    }
    PullToRefreshBox(
        state = stateRefreshState,
        isRefreshing = isRefreshing.value,
        onRefresh = {
            isRefreshing.value = true
            coroutineScope.launch {
                isRefreshing.value = false
                viewModel.onIntent(MainIntent.GetCharacned)
                stateRefreshState.animateToHidden()
            }
        },
    ) {
        Box {
            Column(modifier = Modifier) {
                OutlinedTextField(
                    qery.value,
                    {
                        qery.value = it
                        viewModel.onIntent(
                            MainIntent.GetFilter(
                                name = it,
                                status = if (status.value != "All") status.value else EMPTY_STRING,
                                gender = if (gender.value != "All") gender.value else EMPTY_STRING,
                            )
                        )
                    },
                    textStyle = TextStyle(fontSize = 28.sp),
                    placeholder = { Text("Search") },
                    trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Поиск") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)

                )
                lazyPagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            LoadingComponent()
                        }

                        loadState.append is LoadState.Loading -> {
                            LoadingComponent()
                        }

                        loadState.append is LoadState.Error -> {
                            val error = loadState.append as LoadState.Error
//                    ErrorMessageComponent(error.error.message.toString()) { retry() }
                        }
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        count = lazyPagingItems.itemCount,
                        key = lazyPagingItems.itemKey { it.id },
                        contentType = lazyPagingItems.itemContentType { "contentType" }
                    ) { index ->
                        val item = lazyPagingItems[index]
                        CharacnedComponent(item, goToUser)
                    }
                }
            }
            if (openDialogFilter.value) {
                FullScreenDialogFilter(
                    showDialog = openDialogFilter,
                    gender,
                    status
                ) {
                    viewModel.onIntent(
                        MainIntent.GetFilter(
                            name = qery.value,
                            if (status.value != "All") status.value else EMPTY_STRING,
                            if (gender.value != "All") gender.value else EMPTY_STRING,
                        )
                    )
                    openDialogFilter.value = false
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.BottomEnd),
            onClick = {
                openDialogFilter.value = true
            }) {
            Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Фильтер")
        }


    }

}
