package com.wooyj.picsum.ui.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wooyj.picsum.ui.screen.detail.state.DetailUI
import timber.log.Timber

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Timber.d("DetailScreen: $uiState")

    Scaffold(
        content = { paddingValues ->
            when (uiState) {
                is DetailUIState.Success -> {
                    Timber.d("DetailUIState.Success")
                    DetailUI(
                        modifier = modifier.padding(paddingValues),
                        uiState = (uiState as DetailUIState.Success).ui,
                        clickBefore = { id ->
                            viewModel.onEvent(
                                DetailEvent.OnBeforeClick(id),
                            )
                        },
                        clickNext = { id ->
                            viewModel.onEvent(
                                DetailEvent.OnNextClick(id),
                            )
                        },
                        clickFavorite = { photoId -> viewModel.onEvent(DetailEvent.OnFavoriteClick(photoId)) },
                    )
                }
                else -> Unit
            }
        },
    )
}

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen()
}
