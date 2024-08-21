package com.wooyj.picsum.ui.screen.detail.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wooyj.picsum.ui.common.ImageWithFavorite
import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState
import com.wooyj.picsum.ui.model.ItemId
import com.wooyj.picsum.ui.screen.detail.model.DetailTypeUI
import com.wooyj.picsum.ui.screen.detail.model.nextButtonVisible
import com.wooyj.picsum.ui.screen.detail.model.prevButtonVisible

@Composable
fun DetailUI(
    modifier: Modifier = Modifier,
    uiState: DetailTypeUI,
    clickBefore: (String) -> Unit,
    clickNext: (String) -> Unit,
    clickFavorite: (String) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        ImageWithFavorite(
            uiState = uiState.imageWithFavoriteUIState,
            clickFavorite = clickFavorite,
        )
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            // 이전 버튼
            if (uiState.prevButtonVisible()) {
                Button(
                    modifier = Modifier.align(Alignment.TopStart),
                    onClick = {
                        clickBefore(
                            uiState.prevId.toString(),
                        )
                    },
                ) {
                    Text(text = "이전")
                }
            }
            // 다음 버튼
            if (uiState.nextButtonVisible()) {
                Button(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = {
                        clickNext(
                            uiState.nextId.toString(),
                        )
                    },
                ) {
                    Text(text = "다음")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailUI() {
    val uiState =
        DetailTypeUI(
            ImageWithFavoriteUIState(
                itemId = ItemId("3".toInt()),
                url = "https://picsum.photos/id/3/300/300",
                favorite = false,
            ),
            prevId = "2",
            nextId = "4",
        )
    DetailUI(
        uiState = uiState,
        clickBefore = {},
        clickNext = {},
        clickFavorite = {},
    )
}
