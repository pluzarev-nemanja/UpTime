package com.kumcompany.uptime.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kumcompany.uptime.data.local.dao.WatchDao
import com.kumcompany.uptime.data.local.dao.WatchRemoteKeysDao
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.model.WatchRemoteKeys

@Database(
    entities = [Watch::class, WatchRemoteKeys::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class WatchDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context, useInMemory: Boolean): WatchDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, WatchDatabase::class.java)
            } else {
                Room.databaseBuilder(context, WatchDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun watchDao(): WatchDao
    abstract fun watchRemoteKeysDao(): WatchRemoteKeysDao
}