package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.PicSumWithFavDAOFlow
import com.wooyj.picsum.data.local.room.entity.toPicSumItemFavModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPicSumWithFavRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumWithFavDAOFlow,
    ) : LocalPicSumWithFavRepository {
        override suspend fun getPicSumWithFavoriteList(): Flow<List<com.wooyj.picsum.model.PicSumItemFavModel>> =
            dao
                .getPicSumWithFavoriteList()
                .map { list ->
                    list.map {
                        it.toPicSumItemFavModel()
                    }
                }
    }
