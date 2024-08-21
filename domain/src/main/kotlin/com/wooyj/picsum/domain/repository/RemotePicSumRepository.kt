package com.wooyj.picsum.domain.repository

import androidx.paging.PagingSource
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO

interface RemotePicSumRepository {
    suspend fun getPicSumList(
        page: Int,
        limit: Int,
    ): List<PicSumItemDTO>

    suspend fun getPicSumItem(id: String): PicSumItemDTO?

    fun getPicSumPagingSource(): PagingSource<Int, PicSumItemDTO>
}
