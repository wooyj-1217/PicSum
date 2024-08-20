package com.wooyj.picsum.ui.screen.favorite.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.wooyj.picsum.ui.screen.list.ListEffect
import com.wooyj.picsum.ui.screen.list.ListEvent
import com.wooyj.picsum.ui.screen.list.ListUIState
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI
import com.wooyj.picsum.ui.screen.list.state.ListUI
import timber.log.Timber

@Composable
fun FavoriteListScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteListViewModel = hiltViewModel(),
    onNextNavigation: (String) -> Unit,
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

    when (uiState) {
        ListUIState.Error -> Unit
        ListUIState.Loading -> Unit
        ListUIState.None -> Unit
        is ListUIState.Success -> {
            FavoriteListScreen(
                list = (uiState as ListUIState.Success).list.collectAsLazyPagingItems(),
                onEvent = viewModel::onEvent,
            )
        }
    }
}

@Composable
private fun FavoriteListScreen(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<ListTypeUI>,
    onEvent: (ListEvent) -> Unit,
) {
    Scaffold(
        content = { padding ->
            Timber.d("list: ${list.itemCount}")
            ListUI(
                modifier = modifier.padding(padding),
                list = list,
                clickItem = { photoId ->
                    onEvent(ListEvent.OnItemClickEvent(photoId))
                },
                clickFavorite = { photoId ->
                    onEvent(ListEvent.OnFavClickEvent(photoId))
                },
            )
        },
    )
}

@Preview
@Composable
fun PreviewFavoriteListScreen() {
    FavoriteListScreen(
        onNextNavigation = {},
    )
}
