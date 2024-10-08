package com.wooyj.picsum.data.repository.remote

import androidx.paging.PagingSource
import com.wooyj.picsum.model.PicSum

interface RemotePicSumRepository {
    suspend fun getPicSumList(
        page: Int,
        limit: Int,
    ): List<PicSum>

    suspend fun getPicSumItem(id: String): PicSum?

    fun getPicSumPagingSource(): PagingSource<Int, PicSum>
}
