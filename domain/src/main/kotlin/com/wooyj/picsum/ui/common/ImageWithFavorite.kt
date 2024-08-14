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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageWithFavorite(
    modifier: Modifier = Modifier,
    uiState: ImageWithFavoriteUIState,
    clickFavorite: (String) -> Unit,
) {
    Box(
        modifier =
            modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
    ) {
        AsyncImage(
            modifier =
                modifier.fillMaxSize(),
            model = uiState.url,
            contentDescription = "",
        )
        Image(
            modifier =
                modifier
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

@Preview
@Composable
fun PreviewImageWithFavorite() {
    ImageWithFavorite(
        uiState =
            ImageWithFavoriteUIState(
                photoId = "1",
                url = "https://picsum.photos/200/300",
                favorite = false,
            ),
        clickFavorite = {},
    )
}
