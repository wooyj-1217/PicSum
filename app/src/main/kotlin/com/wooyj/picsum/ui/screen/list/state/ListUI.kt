package com.wooyj.picsum.ui.screen.list.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.wooyj.picsum.ui.screen.list.model.ListTypeUI
import com.wooyj.picsum.ui.screen.list.model.getIcon

@Composable
fun ListUI(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<ListTypeUI>,
    clickItem: (Int) -> Unit,
    clickFavorite: (ListTypeUI) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(
                count = list.itemCount,
                key = { index -> list[index]?.photoId ?: index },
            ) { index ->
                val item = list[index] ?: return@items
                ListItemUI(
                    listTypeUI = item,
                    clickItem = clickItem,
                    clickFavorite = clickFavorite,
                )
            }
        }
    }
}

@Composable
fun ListItemUI(
    listTypeUI: ListTypeUI,
    clickItem: (Int) -> Unit,
    clickFavorite: (ListTypeUI) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable {
                    clickItem(listTypeUI.photoId)
                },
    ) {
        Box {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = listTypeUI.downloadUrl,
                contentDescription = "",
            )
            Image(
                modifier =
                    Modifier
                        .wrapContentSize()
                        .align(
                            alignment = Alignment.TopEnd,
                        ).clickable {
                            clickFavorite(listTypeUI)
                        },
                imageVector = listTypeUI.getIcon(),
                contentDescription = "",
            )
        }
    }
}

@Preview
@Composable
fun PreviewListItemUI() {
    ListItemUI(
        listTypeUI =
            ListTypeUI(
                photoId = 1,
                url = "https://picsum.photos/200/300",
                favorite = false,
                author = "",
                width = 200,
                height = 300,
                downloadUrl = "https://picsum.photos/200/300",
            ),
        clickItem = {},
        clickFavorite = {},
    )
}
