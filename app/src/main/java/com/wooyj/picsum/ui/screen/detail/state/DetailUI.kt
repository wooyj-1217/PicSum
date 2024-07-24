package com.wooyj.picsum.ui.screen.detail.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wooyj.picsum.ui.common.ImageWithFavorite
import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState

@Composable
fun DetailUI(
    modifier: Modifier = Modifier,
    uiState: ImageWithFavoriteUIState,
    clickBefore: () -> Unit,
    clickNext: () -> Unit,
    clickFavorite: (Int) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        ImageWithFavorite(
            uiState = uiState,
            clickFavorite = clickFavorite,
        )
        Row {
            // 이전 버튼
            Button(onClick = clickBefore) {
                Text(text = "이전")
            }
            // 다음 버튼
            Button(onClick = clickNext) {
                Text(text = "다음")
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailUI() {
    DetailUI(
        uiState =
            ImageWithFavoriteUIState(
                photoId = 3,
                url = "https://picsum.photos/id/3/300/300",
                favorite = false,
            ),
        clickBefore = {},
        clickNext = {},
        clickFavorite = {},
    )
}
