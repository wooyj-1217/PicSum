package com.wooyj.picsum.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageWithFavorite(
    uiState: ImageWithFavoriteUIState,
    clickFavorite: (Int) -> Unit,
) {
    Box(
        modifier =
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
    ) {
        AsyncImage(
            modifier =
                Modifier.fillMaxSize(),
            model = uiState.url,
            contentDescription = "",
        )
        Image(
            modifier =
                Modifier
                    .wrapContentSize()
                    .align(
                        alignment = Alignment.TopEnd,
                    ).clickable {
                        clickFavorite(uiState.photoId)
                    },
            imageVector = uiState.getIcon(),
            contentDescription = "",
        )
    }
}
