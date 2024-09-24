package com.wooyj.picsum.feature.favorite.ui.state

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.wooyj.picsum.feature.favorite.ui.model.ListTypeUI
import com.wooyj.picsum.feature.favorite.ui.model.getIcon

@Composable
fun ListUI(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<ListTypeUI>,
    clickItem: (String) -> Unit,
    clickFavorite: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(
                count = list.itemCount,
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
    modifier: Modifier = Modifier,
    listTypeUI: ListTypeUI,
    clickItem: (String) -> Unit,
    clickFavorite: (String) -> Unit,
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
                            clickFavorite(listTypeUI.photoId)
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
                photoId = 1.toString(),
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
