package com.wooyj.picsum.domain.repository.paging

import androidx.paging.PagingSource
import com.wooyj.picsum.data.local.room.entity.PicSumEntity

interface PagingPicSumRepository {
    fun getPicSumPagingSource(): PagingSource<Int, PicSumEntity>
}
