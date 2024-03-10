package com.kumcompany.uptime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kumcompany.uptime.data.local.dao.WatchDao
import com.kumcompany.uptime.domain.model.Watch

@Database(
    entities = [Watch::class],
    version = 1
)
abstract class WatchDatabase: RoomDatabase() {

    abstract fun watchDao():WatchDao
}