package com.wooyj.picsum.data.remote.source

import com.wooyj.picsum.data.remote.dto.PicSumItemDTO
import com.wooyj.picsum.data.remote.service.PicSumAPIService
import com.wooyj.picsum.data.source.PicSumDataSource
import timber.log.Timber
import javax.inject.Inject

class PicSumRemoteDataSource
    @Inject
    constructor(
        private val service: PicSumAPIService,
    ) : PicSumDataSource {
        override suspend fun getPicSumList(
            page: Int,
            limit: Int,
        ): List<PicSumItemDTO> =
            service
                .getPicSumList(page, limit)
                .onFailure {
                    Timber.tag("PicSumRemoteDataSource").d("Failed to get picSum list : $page / $limit")
                }.fold(
                    onSuccess = { it },
                    onFailure = { emptyList() },
                )

        override suspend fun getPicSumItem(id: String): PicSumItemDTO =
            service
                .getPicSumItem(id)
                .onFailure {
                    Timber.tag("PicSumRemoteDataSource").d("Failed to get picSum item : $id")
                }.fold(
                    onSuccess = { it },
                    onFailure = { PicSumItemDTO() },
                )
    }
