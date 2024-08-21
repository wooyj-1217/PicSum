package com.wooyj.picsum.ui.screen.favorite.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wooyj.picsum.ui.screen.detail.DetailEvent
import com.wooyj.picsum.ui.screen.detail.DetailUIState
import com.wooyj.picsum.ui.screen.detail.model.DetailTypeUI
import com.wooyj.picsum.ui.screen.detail.state.DetailUI
import timber.log.Timber

@Composable
fun FavoriteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Timber.d("DetailScreen: $uiState")
    when (uiState) {
        is DetailUIState.Success -> {
            FavoriteDetailScreen(
                modifier = modifier,
                ui = (uiState as DetailUIState.Success).ui,
                onEvent = viewModel::onEvent,
            )
        }
        else -> Unit
    }
}

@Composable
private fun FavoriteDetailScreen(
    modifier: Modifier = Modifier,
    ui: DetailTypeUI,
    onEvent: (DetailEvent) -> Unit,
) {
    Scaffold(
        content = { paddingValues ->
            Timber.d("DetailUIState.Success")
            DetailUI(
                modifier = modifier.padding(paddingValues),
                uiState = ui,
                clickBefore = { id ->
                    onEvent(
                        DetailEvent.OnBeforeClick(id),
                    )
                },
                clickNext = { id ->
                    onEvent(
                        DetailEvent.OnNextClick(id),
                    )
                },
                clickFavorite = { photoId ->
                    onEvent(
                        DetailEvent.OnFavoriteClick(
                            photoId,
                        ),
                    )
                },
            )
        },
    )
}

@Preview
@Composable
fun PreviewFavoriteDetailScreen() {
    FavoriteDetailScreen()
}
