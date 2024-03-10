package com.kumcompany.uptime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kumcompany.uptime.data.local.dao.WatchDao
import com.kumcompany.uptime.data.local.dao.WatchRemoteKeysDao
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.model.WatchRemoteKeys

@Database(
    entities = [Watch::class,WatchRemoteKeys::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class WatchDatabase: RoomDatabase() {

    abstract fun watchDao():WatchDao
    abstract fun watchRemoteKeysDao():WatchRemoteKeysDao
}