package com.kumcompany.uptime.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import com.kumcompany.uptime.data.local.WatchDatabase
import com.kumcompany.uptime.data.remote.FakeWatchApi2
import com.kumcompany.uptime.domain.model.Watch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class WatchRemoteMediatorTest {

    private lateinit var watchApi: FakeWatchApi2
    private lateinit var watchDatabase: WatchDatabase

    @Before
    fun setup(){
        watchApi = FakeWatchApi2()
        watchDatabase = WatchDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup(){
        watchDatabase.clearAllTables()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = WatchRemoteMediator(
                watchApi, watchDatabase
            )
            val pagingState = PagingState<Int,Watch>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(
                LoadType.REFRESH,
                pagingState
            )
            assertTrue(
                result is MediatorResult.Success
            )
            assertFalse(
                (result as MediatorResult.Success).endOfPaginationReached
            )
        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            watchApi.clearData()
            val remoteMediator = WatchRemoteMediator(
                watchApi, watchDatabase
            )
            val pagingState = PagingState<Int,Watch>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(
                LoadType.REFRESH,
                pagingState
            )
            assertTrue(
                result is MediatorResult.Success
            )
            assertTrue(
                (result as MediatorResult.Success).endOfPaginationReached
            )
        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runBlocking {
            watchApi.addException()
            val remoteMediator = WatchRemoteMediator(
                watchApi, watchDatabase
            )
            val pagingState = PagingState<Int,Watch>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result = remoteMediator.load(
                LoadType.REFRESH,
                pagingState
            )
            assertTrue(
                result is MediatorResult.Error
            )
        }
}