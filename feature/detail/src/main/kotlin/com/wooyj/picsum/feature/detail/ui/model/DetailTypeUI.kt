package com.wooyj.picsum.feature.detail.ui.model

import com.wooyj.picsum.model.ItemId
import com.wooyj.picsum.model.ItemWithIdModel
import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState

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
                url = item!!.downloadUrl,
                favorite = item!!.favorite,
            ),
        prevId =
            if (ItemId(item!!.id.toInt()).prev()?.getId() == null) {
                null
            } else {
                ItemId(item!!.id.toInt()).prev()?.getId().toString()
            },
        nextId =
            if (ItemId(item!!.id.toInt()).next()?.getId() == null) {
                null
            } else {
                ItemId(item!!.id.toInt()).next()?.getId().toString()
            },
    )

fun ItemWithIdModel.toFavoriteDetailTypeUI() =
    DetailTypeUI(
        imageWithFavoriteUIState =
            ImageWithFavoriteUIState(
                itemId = ItemId(item!!.id.toInt()),
                url = item!!.downloadUrl,
                favorite = item!!.favorite,
            ),
        prevId = prevId,
        nextId = nextId,
    )

fun DetailTypeUI.prevButtonVisible() = prevId != null

fun DetailTypeUI.nextButtonVisible() = nextId != null
