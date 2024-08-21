package com.wooyj.picsum.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wooyj.picsum.data.local.room.entity.PicSumWithFavEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PicSumWithFavDAOFlow {
    @Query(
        """
    SELECT * FROM pic_sum
    WHERE id IN (SELECT id FROM favorite WHERE visible = 1)
    ORDER BY (SELECT _id FROM favorite WHERE favorite.id = pic_sum.id)
    """,
    )
    fun getPicSumWithFavoriteList(): Flow<List<PicSumWithFavEntity>>
}
