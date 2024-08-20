package com.wooyj.picsum.ui.screen.detail.model

import com.wooyj.picsum.domain.model.ItemWithIdModel
import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState
import com.wooyj.picsum.ui.model.ItemId

data class DetailTypeUI(
    val imageWithFavoriteUIState: ImageWithFavoriteUIState,
)

fun ItemWithIdModel.toDetailTypeUI() =
    DetailTypeUI(
        imageWithFavoriteUIState =
            ImageWithFavoriteUIState(
                itemId = ItemId(item!!.id.toInt()),
                url = item.downloadUrl,
                favorite = item.favorite,
            ),
    )

fun DetailTypeUI.prevButtonVisible() = imageWithFavoriteUIState.itemId.prev() != null

fun DetailTypeUI.nextButtonVisible() = imageWithFavoriteUIState.itemId.next() != null
