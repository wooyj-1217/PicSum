package com.wooyj.picsum.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wooyj.picsum.data.remote.dto.PicSumItemDTO
import com.wooyj.picsum.data.source.PicSumDataSource
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import javax.inject.Inject

class RemotePicSumRepositoryImpl
    @Inject
    constructor(
        private val dataSource: PicSumDataSource,
    ) : RemotePicSumRepository {
        override suspend fun getPicSumList(
            page: Int,
            limit: Int,
        ): List<PicSumItemDTO> = dataSource.getPicSumList(page, limit)

        override suspend fun getPicSumItem(id: String): PicSumItemDTO = dataSource.getPicSumItem(id)

        override fun getPicSumPagingSource(): PagingSource<Int, PicSumItemDTO> =
            object : PagingSource<Int, PicSumItemDTO>() {
                override fun getRefreshKey(state: PagingState<Int, PicSumItemDTO>): Int? =
                    state.anchorPosition?.let { anchorPosition ->
                        val anchorPage = state.closestPageToPosition(anchorPosition)
                        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                    }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PicSumItemDTO> {
                    val page = params.key ?: 1
                    return try {
                        val response = dataSource.getPicSumList(page, params.loadSize)
                        LoadResult.Page(
                            data = response,
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = if (response.isEmpty()) null else page + 1,
                        )
                    } catch (e: Exception) {
                        LoadResult.Error(e)
                    }
                }
            }
    }
