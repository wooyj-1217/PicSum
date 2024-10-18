package com.wooyj.picsum.feature.detail.domain

import com.wooyj.picsum.model.ItemWithIdModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * 리스트 > 상세용 use case
 */
@Reusable
class DetailUseCase
    @Inject
    constructor(
        private val currentItemUseCase: GetCurrentItemUseCase,
        private val nextIdUseCase: GetNextIdUseCase,
        private val prevIdUseCase: GetPrevIdUseCase,
    ) {
        operator fun invoke(currentId: String): Flow<ItemWithIdModel> =
            combine(currentItemUseCase(currentId), nextIdUseCase(currentId), prevIdUseCase(currentId)) { current, next, prev ->
                ItemWithIdModel(
                    prevId = prev,
                    nextId = next,
                    item = current,
                )
            }
    }

// 1) Detail
// 1-1) cache list 값이 없으면 return empty
// 1-2) before id : list의 이전 id 있으면 작성, 없으면 null
// 1-3) after id : list의 다음 id 있으면 작성, 없으면 null
// 2) Favorite
// 2-1) Favorite list 값이 없으면 return empty
// 2-2) currentId
// 2-2-1) 있을 경우 : currentId를 선택
// 2-2-1) 없을 경우 : favoriteList의 첫번째 id를 선택
// 2-3) before id : list의 이전 id 있으면 작성, 없으면 null
// 2-4) after id : list의 다음 id 있으면 작성, 없으면 null
