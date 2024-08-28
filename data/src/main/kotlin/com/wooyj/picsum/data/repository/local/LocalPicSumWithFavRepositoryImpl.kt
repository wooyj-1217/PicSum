package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.PicSumWithFavDAOFlow
import com.wooyj.picsum.data.local.room.entity.toPicSumItemFavModel
import com.wooyj.picsum.domain.model.PicSumItemFavModel
import com.wooyj.picsum.domain.repository.local.LocalPicSumWithFavRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPicSumWithFavRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumWithFavDAOFlow,
    ) : LocalPicSumWithFavRepository {
        override suspend fun getPicSumWithFavoriteList(): Flow<List<PicSumItemFavModel>> =
            dao
                .getPicSumWithFavoriteList()
                .map { list ->
                    list.map {
                        it.toPicSumItemFavModel()
                    }
                }
    }
