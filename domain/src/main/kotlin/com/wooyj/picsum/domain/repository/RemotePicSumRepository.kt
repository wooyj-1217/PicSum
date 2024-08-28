package com.wooyj.picsum.domain.repository

import androidx.paging.PagingSource
import com.wooyj.picsum.domain.model.PicSum

interface RemotePicSumRepository {
    suspend fun getPicSumList(
        page: Int,
        limit: Int,
    ): List<PicSum>

    suspend fun getPicSumItem(id: String): PicSum?

    fun getPicSumPagingSource(): PagingSource<Int, PicSum>
}
