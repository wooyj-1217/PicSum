package com.wooyj.picsum.data.source

import com.wooyj.picsum.data.remote.dto.PicSumItemDTO

interface PicSumRemoteDataSource {
    suspend fun getPicSumList(
        page: Int,
        limit: Int,
    ): List<PicSumItemDTO>

    suspend fun getPicSumItem(id: String): PicSumItemDTO
}
