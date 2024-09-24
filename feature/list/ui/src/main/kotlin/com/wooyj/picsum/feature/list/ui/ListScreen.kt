package com.wooyj.picsum.feature.list.ui

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
import com.wooyj.picsum.feature.list.ui.model.ListTypeUI
import com.wooyj.picsum.feature.list.ui.state.ListUI
import timber.log.Timber

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel(),
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
            ListScreen(list = (uiState as ListUIState.Success).list.collectAsLazyPagingItems(), onEvent = viewModel::onEvent)
        }
    }
}

@Composable
private fun ListScreen(
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
fun PreviewListScreen() {
    ListScreen(
        onNextNavigation = {},
    )
}
