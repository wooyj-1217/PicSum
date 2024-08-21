package com.wooyj.picsum.domain.usecase.detail

import com.wooyj.picsum.domain.model.ItemWithIdModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
            flow {
                // 1) 현재 item 조회 (cache 아이템이 있을경우 cache 아이템 조회, 없을 경우 remote 조회 및 save)
                val currentItem = currentItemUseCase(currentId)

                // 2) 현재 아이템의 이전, 다음 id값 찾기
                // 아이디 조회 -> 현재 X
                // 앞 뒤 -> DB -> remote -> retry : 3, limit
                // 현재 : 아이템조회-> DB에 찔러보고 -> 없으면 remote -> null
                // prev, next : 아이템조회-> DB에 찔러보고 -> 없으면 remote -> null
                // 내 앞번호 뭐야?? ->
                // 내 뒷번호 뭐야?? -> Server에서 데이터 와야 한다
                // 그냥 찌른다 -> DB, Server

                val prevId = prevIdUseCase(currentId)
                val nextId = nextIdUseCase(currentId)

                emit(
                    ItemWithIdModel(
                        prevId = prevId,
                        nextId = nextId,
                        item = currentItem,
                    ),
                )
//                val favListFlow = favoriteListUseCase() // Flow<List<FavoriteEntity>>
//                val cacheList = picSumListUseCase() // List<PicSumEntity> ( cache List )
//
//                combine(favListFlow, currentId) { favList, id ->
//                    Timber.d("Favorite List: $favList")
//
//                    val selectedId =
//                        if (id == "") {
//                            favList.first().id
//                        } else {
//                            id
//                        }
//
//                    val list =
//                        if (fromDetail) {
//                            cacheList.map { it.id }
//                        } else {
//                            favList.map { it.id }
//                        }
//
//                    Timber.d("currentId: $id")
//                    Timber.d("selectedId: $selectedId")
//
//                    val model =
//                        if (cacheList.isEmpty()) {
//                            picSumItemUseCase(selectedId).toPicSumItemFavModel(
//                                isFavorite = favList.any { it.id == selectedId && it.visible },
//                            )
//                        } else {
//                            cacheList.first { it.id == selectedId }.toPicSumItemFavModel(
//                                isFavorite = favList.any { it.id == selectedId && it.visible },
//                            )
//                        }
//
//                    val beforeId = list.getOrNull(list.indexOf(selectedId) - 1)
//                    val nextId = list.getOrNull(list.indexOf(selectedId) + 1)
//
//                    ItemWithIdModel(
//                        beforeId = beforeId,
//                        nextId = nextId,
//                        item = model,
//                    ).also {
//                        Timber.d("Emitting model: $it")
//                    }
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
