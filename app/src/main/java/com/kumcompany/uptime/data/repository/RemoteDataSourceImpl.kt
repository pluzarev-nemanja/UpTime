package com.kumcompany.uptime.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kumcompany.uptime.data.local.WatchDatabase
import com.kumcompany.uptime.data.paging_source.WatchRemoteMediator
import com.kumcompany.uptime.data.remote.WatchApi
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.repository.RemoteDataSource
import com.kumcompany.uptime.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val watchApi: WatchApi,
    private val watchDatabase: WatchDatabase
): RemoteDataSource {

    private val watchDao = watchDatabase.watchDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllWatches(): Flow<PagingData<Watch>> {
        val pagingSourceFactory = {watchDao.getAllWatches()}
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE
            ),
            remoteMediator = WatchRemoteMediator(
                watchApi = watchApi,
                watchDatabase = watchDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchWatches(query: String): Flow<PagingData<Watch>> {
        TODO("Not yet implemented")
    }
}