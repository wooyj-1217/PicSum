package com.wooyj.picsum.data.repository.paging

import androidx.paging.PagingSource
import com.wooyj.picsum.data.paging.ListPagerSource
import com.wooyj.picsum.domain.model.PicSum
import com.wooyj.picsum.domain.repository.RemotePicSumRepository
import com.wooyj.picsum.domain.repository.local.LocalPicSumRepository
import com.wooyj.picsum.domain.repository.paging.PagingPicSumRepository
import javax.inject.Inject

class PagingPicSumRepositoryImpl
    @Inject
    constructor(
        private val localRepo: LocalPicSumRepository,
        private val remoteRepo: RemotePicSumRepository,
    ) : PagingPicSumRepository {
        override fun getPicSumPagingSource(): PagingSource<Int, PicSum> = ListPagerSource(localRepo, remoteRepo)
    }
