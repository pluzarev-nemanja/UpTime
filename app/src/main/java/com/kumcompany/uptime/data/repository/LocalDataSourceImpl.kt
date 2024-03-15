package com.kumcompany.uptime.data.repository

import com.kumcompany.uptime.data.local.WatchDatabase
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.repository.LocalDataSource

class LocalDataSourceImpl(
    watchDatabase: WatchDatabase
): LocalDataSource {

    private val watchDao = watchDatabase.watchDao()
    override suspend fun getSelectedWatch(watchId: Int): Watch {
        return watchDao.getSelectedWatch(watchId)
    }
}