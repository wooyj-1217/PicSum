package com.wooyj.picsum.feature.favdetail.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wooyj.picsum.feature.favdetail.ui.model.DetailTypeUI
import com.wooyj.picsum.feature.favdetail.ui.state.FavDetailUI
import timber.log.Timber

@Composable
fun FavoriteDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Timber.d("DetailScreen: $uiState")
    when (uiState) {
        is FavDetailUIState.Success -> {
            FavoriteDetailScreen(
                modifier = modifier,
                ui = (uiState as FavDetailUIState.Success).ui,
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
    onEvent: (FavDetailEvent) -> Unit,
) {
    Scaffold(
        content = { paddingValues ->
            Timber.d("DetailUIState.Success")
            FavDetailUI(
                modifier = modifier.padding(paddingValues),
                uiState = ui,
                clickBefore = { id ->
                    onEvent(
                        FavDetailEvent.OnBeforeClick(id),
                    )
                },
                clickNext = { id ->
                    onEvent(
                        FavDetailEvent.OnNextClick(id),
                    )
                },
                clickFavorite = { photoId ->
                    onEvent(
                        FavDetailEvent.OnFavoriteClick(
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
