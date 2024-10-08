package com.wooyj.picsum.feature.favdetail.domain

import com.wooyj.picsum.domain.usecase.detail.GetFavNextIdUseCase
import com.wooyj.picsum.domain.usecase.detail.GetFavPrevIdUseCase
import dagger.Reusable
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class FavoriteDetailUseCase
    @Inject
    constructor(
        private val currentItemUseCase: GetCurrentVisibleItemUseCase,
        private val nextIdUseCase: GetFavNextIdUseCase,
        private val prevIdUseCase: GetFavPrevIdUseCase,
    ) {
        operator fun invoke(currentId: String) =
            combine(currentItemUseCase(currentId), nextIdUseCase(currentId), prevIdUseCase(currentId))
            { currentItem, nextId, prevId ->
                com.wooyj.picsum.model.ItemWithIdModel(
                    prevId = prevId,
                    nextId = nextId,
                    item = currentItem,
                )
            }
    }
