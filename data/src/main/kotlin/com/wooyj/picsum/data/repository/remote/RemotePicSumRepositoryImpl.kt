package com.wooyj.picsum.data.repository.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wooyj.picsum.data.remote.dto.toPicSum
import com.wooyj.picsum.data.source.PicSumRemoteDataSource
import com.wooyj.picsum.model.PicSum
import javax.inject.Inject

class RemotePicSumRepositoryImpl
    @Inject
    constructor(
        private val dataSource: PicSumRemoteDataSource,
    ) : RemotePicSumRepository {
        override suspend fun getPicSumList(
            page: Int,
            limit: Int,
        ): List<com.wooyj.picsum.model.PicSum> = dataSource.getPicSumList(page, limit).map { it.toPicSum() }

        override suspend fun getPicSumItem(id: String): PicSum? = dataSource.getPicSumItem(id).toPicSum()

        override fun getPicSumPagingSource(): PagingSource<Int, com.wooyj.picsum.model.PicSum> =
            object : PagingSource<Int, com.wooyj.picsum.model.PicSum>() {
                override fun getRefreshKey(state: PagingState<Int, com.wooyj.picsum.model.PicSum>): Int? =
                    state.anchorPosition?.let { anchorPosition ->
                        val anchorPage = state.closestPageToPosition(anchorPosition)
                        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
                    }

                override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.wooyj.picsum.model.PicSum> {
                    val page = params.key ?: 1
                    return try {
                        val response = dataSource.getPicSumList(page, params.loadSize).map { it.toPicSum() }
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
