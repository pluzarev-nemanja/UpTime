package com.kumcompany.uptime.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kumcompany.uptime.util.Constants.WATCH_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = WATCH_REMOTE_KEYS_DATABASE_TABLE)
data class WatchRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?,
    val lastUpdated : Long?
)