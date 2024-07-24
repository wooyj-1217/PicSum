package com.wooyj.picsum.ui.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wooyj.picsum.ui.screen.detail.state.DetailUI
import com.wooyj.picsum.ui.screen.detail.viewmodel.DetailViewModel

@Composable
fun DetailScreen(viewModel: DetailViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effect by viewModel.effect.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(key1 = effect) {
        when (effect) {
            is DetailEffect.NavigateToBefore -> {
                // 이전 정보 가져오기
            }

            is DetailEffect.NavigateToNext -> {
                // 다음 정보 가져오기
            }

            else -> Unit
        }
    }

    Scaffold(
        content = { paddingValues ->
            when (uiState) {
                is DetailUIState.Success -> {
                    DetailUI(
                        modifier = Modifier.padding(paddingValues),
                        uiState = (uiState as DetailUIState.Success).imageWithFavoriteUIState,
                        clickBefore = { viewModel.onEvent(DetailEvent.OnBeforeClick) },
                        clickNext = { viewModel.onEvent(DetailEvent.OnNextClick) },
                        clickFavorite = { viewModel.onEvent(DetailEvent.OnFavoriteClick) },
                    )
                }
                else -> Unit
            }
        },
    )
}
