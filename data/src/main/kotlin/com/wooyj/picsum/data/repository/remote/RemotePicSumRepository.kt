package com.wooyj.picsum.data.repository.remote

import androidx.paging.PagingSource

interface RemotePicSumRepository {
    suspend fun getPicSumList(
        page: Int,
        limit: Int,
    ): List<com.wooyj.picsum.model.PicSum>

    suspend fun getPicSumItem(id: String): com.wooyj.picsum.model.PicSum?

    fun getPicSumPagingSource(): PagingSource<Int, com.wooyj.picsum.model.PicSum>
}
