package com.wooyj.picsum.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import com.wooyj.picsum.model.PicSum
import timber.log.Timber
import javax.inject.Inject

class ListPagerSource
    @Inject
    constructor(
        private val localRepo: LocalPicSumRepository,
        private val remoteRepo: RemotePicSumRepository,
    ) : PagingSource<Int, com.wooyj.picsum.model.PicSum>() {
        override fun getRefreshKey(state: PagingState<Int, com.wooyj.picsum.model.PicSum>): Int? =
            state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, com.wooyj.picsum.model.PicSum> {
            val page = params.key ?: 1
            try {
                // page Print
                Timber.d("page: $page")

                // local에서 데이터를 가져옴
                val localData =
                    localRepo
                        .getPicSumListPaging(
                            offset = (page - 1) * params.loadSize,
                            limit = params.loadSize,
                        )

                // local 데이터가 있으면 localData 반환
                if (localData.isNotEmpty()) {
                    val result =
                        LoadResult
                            .Page(
                                data = localData,
                                prevKey = if (page == 1) null else page - 1,
                                nextKey = if (localData.isEmpty()) null else page + 1,
                            )
                    Timber.d("localData result: $result")
                    return result
                }

                // remote에서 데이터를 가져옴
                val response = remoteRepo.getPicSumList(page, params.loadSize)
                Timber.d("response: $response")

                // local에 데이터 저장
                localRepo.insert(response)
                Timber.d("Data inserted locally")

                // remote에서 가져온 데이터를 반환
                val result =
                    LoadResult
                        .Page(
                            data = response,
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = if (response.isEmpty()) null else page + 1,
                        )
                return result
            } catch (e: Exception) {
                Timber.e("ListPagerSource", "Error loading data", e)
                return LoadResult.Error(e)
            }
        }
    }
