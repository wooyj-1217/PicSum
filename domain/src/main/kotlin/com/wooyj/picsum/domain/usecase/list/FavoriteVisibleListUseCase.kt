package com.wooyj.picsum.domain.usecase.list

import com.wooyj.picsum.model.Favorite
import kotlinx.coroutines.flow.Flow

fun interface FavoriteVisibleListUseCase {
    operator fun invoke(): Flow<List<Favorite>>
}
