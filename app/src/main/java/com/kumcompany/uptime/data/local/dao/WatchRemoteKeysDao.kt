package com.kumcompany.uptime.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kumcompany.uptime.domain.model.WatchRemoteKeys

@Dao
interface WatchRemoteKeysDao {

    @Query("SELECT * FROM watch_remote_keys_table WHERE id=:id")
    suspend fun getRemoteKey(id:Int):WatchRemoteKeys?

    @Upsert
    suspend fun addAllRemoteKeys(watchRemoteKeys: List<WatchRemoteKeys>)

    @Query("DELETE FROM watch_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}