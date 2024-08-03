package com.wooyj.picsum.ui.screen.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.wooyj.picsum.ui.screen.list.state.ListUI
import timber.log.Timber

@Composable
fun ListScreen(
    viewModel: ListViewModel = hiltViewModel(),
    onNextNavigation: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = effect) {
        when (effect) {
            is ListEffect.NavigateToDetail -> {
                onNextNavigation((effect as ListEffect.NavigateToDetail).photoId)
            }

            else -> Unit
        }
    }

    Scaffold(
        content = { padding ->
            when (uiState) {
                is ListUIState.Success -> {
                    val list = (uiState as ListUIState.Success).list.collectAsLazyPagingItems()
                    Timber.d("list: ${list.itemCount}")
                    ListUI(
                        modifier = Modifier.padding(padding),
                        list = list,
                        clickItem = { photoId ->
                            viewModel.onEvent(ListEvent.OnItemClickEvent(photoId))
                        },
                        clickFavorite = { photoId ->
                            viewModel.onEvent(ListEvent.OnFavClickEvent(photoId))
                        },
                    )
                }

                else -> Unit
            }
        },
    )
}
