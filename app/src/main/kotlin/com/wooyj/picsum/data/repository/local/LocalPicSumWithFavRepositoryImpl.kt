package com.wooyj.picsum.data.repository.local

import com.wooyj.picsum.data.local.room.dao.PicSumWithFavDAOFlow
import com.wooyj.picsum.data.local.room.entity.PicSumWithFavEntity
import com.wooyj.picsum.domain.repository.local.LocalPicSumWithFavRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalPicSumWithFavRepositoryImpl
    @Inject
    constructor(
        private val dao: PicSumWithFavDAOFlow,
    ) : LocalPicSumWithFavRepository {
        override suspend fun getPicSumWithFavoriteList(): Flow<List<PicSumWithFavEntity>> = dao.getPicSumWithFavoriteList()
    }
