package com.wooyj.picsum.domain.repository.paging

import androidx.paging.PagingSource
import com.wooyj.picsum.model.PicSum

interface PagingPicSumRepository {
    fun getPicSumPagingSource(): PagingSource<Int, com.wooyj.picsum.model.PicSum>
}
