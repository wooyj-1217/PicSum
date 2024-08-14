package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.domain.model.ItemWithIdModel
import com.wooyj.picsum.domain.model.toPicSumItemFavModel
import com.wooyj.picsum.domain.usecase.list.FavoriteListUseCase
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import timber.log.Timber
import javax.inject.Inject

/**
 * 리스트 > 상세용 use case
 */
@Reusable
class DetailUseCase
    @Inject
    constructor(
        private val picSumListUseCase: PicSumListUseCase, // local(cache List)
        private val favoriteListUseCase: FavoriteListUseCase, // local(fav id List)
        private val picSumItemUseCase: GetPicSumItemUseCase, // remote(id)
    ) {
        suspend operator fun invoke(
            fromDetail: Boolean,
            currentId: StateFlow<String>,
        ): Flow<ItemWithIdModel> {
            val favListFlow = favoriteListUseCase() // Flow<List<FavoriteEntity>>
            val cacheList = picSumListUseCase() // List<PicSumEntity> ( cache List )

            return combine(favListFlow, currentId) { favList, id ->
                Timber.d("Favorite List: $favList")

                val selectedId =
                    if (id == "") {
                        favList.first().id
                    } else {
                        id
                    }

                val list =
                    if (fromDetail) {
                        cacheList.map { it.id }
                    } else {
                        favList.map { it.id }
                    }

                Timber.d("currentId: $id")
                Timber.d("selectedId: $selectedId")

                val model =
                    if (cacheList.isEmpty()) {
                        picSumItemUseCase(selectedId).toPicSumItemFavModel(
                            isFavorite = favList.any { it.id == selectedId && it.visible },
                        )
                    } else {
                        cacheList.first { it.id == selectedId }.toPicSumItemFavModel(
                            isFavorite = favList.any { it.id == selectedId && it.visible },
                        )
                    }

                val beforeId = list.getOrNull(list.indexOf(selectedId) - 1)
                val nextId = list.getOrNull(list.indexOf(selectedId) + 1)

                ItemWithIdModel(
                    beforeId = beforeId,
                    nextId = nextId,
                    item = model,
                ).also {
                    Timber.d("Emitting model: $it")
                }
            }
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
