package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.domain.usecase.detail.GetFavNextIdUseCase
import com.wooyj.picsum.domain.usecase.detail.GetFavPrevIdUseCase
import dagger.Reusable
import kotlinx.coroutines.flow.combine
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
            combine(currentItemUseCase(currentId), nextIdUseCase(currentId), prevIdUseCase(currentId)) { currentItem, nextId, prevId ->
                com.wooyj.picsum.model.ItemWithIdModel(
                    prevId = prevId,
                    nextId = nextId,
                    item = currentItem,
                )
            }
    }
