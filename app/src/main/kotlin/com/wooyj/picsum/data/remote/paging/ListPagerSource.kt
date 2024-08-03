package com.wooyj.picsum.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wooyj.picsum.data.local.room.entity.PicSumEntity
import com.wooyj.picsum.data.remote.service.PicSumAPIService
import com.wooyj.picsum.domain.repository.LocalPicSumRepository

class ListPagerSource constructor(
    private val service: PicSumAPIService,
    private val localPicSumRepository: LocalPicSumRepository,
    private val limit: Int,
) : PagingSource<Int, PicSumEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PicSumEntity>): Int? =
        state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicSumEntity> {
        val page = params.key ?: 1
        val limit = params.loadSize

        localPicSumRepository.getFavoriteList()

        service.getPicSumList()

        return LoadResult.Page(
            data = response,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (response.isEmpty()) null else page + 1,
        )
    }
}
