package com.wooyj.picsum.domain.repository.paging

import androidx.paging.PagingSource
import com.wooyj.picsum.domain.model.PicSum

interface PagingPicSumRepository {
    fun getPicSumPagingSource(): PagingSource<Int, PicSum>
}
