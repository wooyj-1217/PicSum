package com.wooyj.picsum.domain.usecase.favorite

import com.wooyj.picsum.domain.model.ItemWithIdModel
import com.wooyj.picsum.domain.usecase.local.favorite.GetFavNextIdUseCase
import com.wooyj.picsum.domain.usecase.local.favorite.GetFavPrevIdUseCase
import dagger.Reusable
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
        suspend operator fun invoke(currentId: String) =
            flow {
                val currentItem = currentItemUseCase(currentId)

                // nextId와 prevId 가져오기
                val nextId = nextIdUseCase(currentId)
                val prevId = prevIdUseCase(currentId)

                emit(
                    ItemWithIdModel(
                        prevId = prevId,
                        nextId = nextId,
                        item = currentItem,
                    ),
                )
            }
    }
