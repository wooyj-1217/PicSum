package com.wooyj.picsum.domain.repository

import androidx.paging.PagingSource
import com.wooyj.picsum.model.PicSum

interface RemotePicSumRepository {
    suspend fun getPicSumList(
        page: Int,
        limit: Int,
    ): List<com.wooyj.picsum.model.PicSum>

    suspend fun getPicSumItem(id: String): com.wooyj.picsum.model.PicSum?

    fun getPicSumPagingSource(): PagingSource<Int, com.wooyj.picsum.model.PicSum>
}
