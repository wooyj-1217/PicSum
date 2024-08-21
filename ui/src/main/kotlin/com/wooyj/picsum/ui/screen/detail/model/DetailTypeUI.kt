package com.wooyj.picsum.ui.screen.detail.model

import com.wooyj.picsum.domain.model.ItemWithIdModel
import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState
import com.wooyj.picsum.ui.model.ItemId

data class DetailTypeUI(
    val imageWithFavoriteUIState: ImageWithFavoriteUIState,
    val prevId: String?,
    val nextId: String?,
)

fun ItemWithIdModel.toDetailTypeUI() =
    DetailTypeUI(
        imageWithFavoriteUIState =
            ImageWithFavoriteUIState(
                itemId = ItemId(item!!.id.toInt()),
                url = item.downloadUrl,
                favorite = item.favorite,
            ),
        prevId =
            if (ItemId(item.id.toInt()).prev()?.getId() == null) {
                null
            } else {
                ItemId(item.id.toInt()).prev()?.getId().toString()
            },
        nextId =
            if (ItemId(item.id.toInt()).next()?.getId() == null) {
                null
            } else {
                ItemId(item.id.toInt()).next()?.getId().toString()
            },
    )

fun ItemWithIdModel.toFavoriteDetailTypeUI() =
    DetailTypeUI(
        imageWithFavoriteUIState =
            ImageWithFavoriteUIState(
                itemId = ItemId(item!!.id.toInt()),
                url = item.downloadUrl,
                favorite = item.favorite,
            ),
        prevId = prevId,
        nextId = nextId,
    )

fun DetailTypeUI.prevButtonVisible() = prevId != null

fun DetailTypeUI.nextButtonVisible() = nextId != null
