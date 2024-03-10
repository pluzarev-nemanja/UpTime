package com.kumcompany.uptime.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kumcompany.uptime.domain.model.Watch

@Dao
interface WatchDao {

    @Query("SELECT * FROM watch_table ORDER BY id ASC")
    fun getAllWatches():PagingSource<Int,Watch>

    @Query("SELECT * FROM watch_table WHERE id=:watchId")
    fun getSelectedWatch(watchId : Int):Watch

    @Upsert
    suspend fun addWatches(watches: List<Watch>)

    @Query("DELETE FROM watch_table")
    suspend fun deleteAllWatches()
}