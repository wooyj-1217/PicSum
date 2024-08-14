package com.wooyj.picsum.data.remote.service

import com.wooyj.picsum.data.remote.dto.PicSumItemDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicSumAPIService {
    @GET("v2/list")
    suspend fun getPicSumList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Result<List<PicSumItemDTO>>

    @GET("id/{id}/info")
    suspend fun getPicSumItem(
        @Path("id") id: String,
    ): Result<PicSumItemDTO>
}
