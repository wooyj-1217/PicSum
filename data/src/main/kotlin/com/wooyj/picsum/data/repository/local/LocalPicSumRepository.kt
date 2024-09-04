package com.wooyj.picsum.data.repository.local

interface LocalPicSumRepository {
    suspend fun insert(list: List<com.wooyj.picsum.model.PicSum>)

    suspend fun insert(entity: com.wooyj.picsum.model.PicSum): Long?

    suspend fun deleteAll()

    suspend fun getPicSumListPaging(
        offset: Int,
        limit: Int,
    ): List<com.wooyj.picsum.model.PicSum>

    suspend fun getPicSumList(): List<com.wooyj.picsum.model.PicSum>

    suspend fun getPicSumItem(id: String): com.wooyj.picsum.model.PicSum?

    suspend fun getPrevId(currentId: String): String?

    suspend fun getNextId(currentId: String): String?
}
