package com.wooyj.picsum.data.repository.paging

import androidx.paging.PagingSource

interface PagingPicSumRepository {
    fun getPicSumPagingSource(): PagingSource<Int, com.wooyj.picsum.model.PicSum>
}
