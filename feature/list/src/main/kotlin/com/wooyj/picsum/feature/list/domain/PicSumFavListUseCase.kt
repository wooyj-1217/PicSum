package com.wooyj.picsum.feature.list.domain

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.wooyj.picsum.domain.usecase.list.PicSumListPagingUseCase
import com.wooyj.picsum.domain.usecase.local.favorite.FavoriteVisibleListUseCase
import com.wooyj.picsum.model.PicSumItemFavModel
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@Reusable
class PicSumFavListUseCase
    @Inject
    constructor(
        private val listUseCase: PicSumListPagingUseCase,
        private val favListUseCase: FavoriteVisibleListUseCase,
    ) {
        operator fun invoke(
            limit: Int,
            scope: CoroutineScope,
        ): Flow<PagingData<PicSumItemFavModel>> {
            val listFlow =
                listUseCase(limit = limit)
                    .cachedIn(scope = scope)
            val favFlow = favListUseCase()

            return combine(listFlow, favFlow) { list, favList ->
                list
                    .map { item ->
                        PicSumItemFavModel(
                            id = item.id,
                            author = item.author,
                            width = item.width,
                            height = item.height,
                            url = item.url,
                            downloadUrl = item.downloadUrl,
                            favorite = favList.any { it.id == item.id },
                        )
                    }
            }.cachedIn(scope)
        }
    }
