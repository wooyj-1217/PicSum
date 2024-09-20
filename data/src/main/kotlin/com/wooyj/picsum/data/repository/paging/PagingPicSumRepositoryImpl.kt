package com.wooyj.picsum.data.repository.paging

import androidx.paging.PagingSource
import com.wooyj.picsum.data.paging.ListPagerSource
import com.wooyj.picsum.data.repository.local.LocalPicSumRepository
import com.wooyj.picsum.data.repository.remote.RemotePicSumRepository
import javax.inject.Inject

class PagingPicSumRepositoryImpl
    @Inject
    constructor(
        private val localRepo: LocalPicSumRepository,
        private val remoteRepo: RemotePicSumRepository,
    ) : PagingPicSumRepository {
        override fun getPicSumPagingSource(): PagingSource<Int, com.wooyj.picsum.model.PicSum> = ListPagerSource(localRepo, remoteRepo)
    }
