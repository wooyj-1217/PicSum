package com.wooyj.picsum.ui.screen.detail.model

import com.wooyj.picsum.domain.model.ItemWithIdModel
import com.wooyj.picsum.ui.common.ImageWithFavoriteUIState

data class DetailTypeUI(
    val imageWithFavoriteUIState: ImageWithFavoriteUIState,
    val beforeId: String?,
    val nextId: String?,
)

fun ItemWithIdModel.toDetailTypeUI() =
    DetailTypeUI(
        imageWithFavoriteUIState =
            ImageWithFavoriteUIState(
                photoId = item!!.id,
                url = item.downloadUrl,
                favorite = item.favorite,
            ),
        beforeId = beforeId,
        nextId = nextId,
    )

// max : 1084, min :0
fun DetailTypeUI.beforeButtonVisible() = beforeId != null

fun DetailTypeUI.nextButtonVisible() = nextId != null
