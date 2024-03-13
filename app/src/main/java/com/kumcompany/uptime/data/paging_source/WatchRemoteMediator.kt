package com.kumcompany.uptime.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.kumcompany.uptime.data.local.WatchDatabase
import com.kumcompany.uptime.data.remote.WatchApi
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.model.WatchRemoteKeys
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class WatchRemoteMediator @Inject constructor(
    private val watchApi: WatchApi,
    private val watchDatabase: WatchDatabase
): RemoteMediator<Int,Watch>() {

    private val watchDao = watchDatabase.watchDao()
    private val watchRemoteKeysDao = watchDatabase.watchRemoteKeysDao()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Watch>): MediatorResult {
       return try {
           val page = when(loadType){
               LoadType.REFRESH->{
                   val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                   remoteKeys?.nextPage?.minus(1) ?: 1
               }
               LoadType.PREPEND->{
                   val remoteKeys = getRemoteKeyForFirstItem(state)
                   val prevPage = remoteKeys?.prevPage
                       ?: return MediatorResult.Success(
                           endOfPaginationReached = remoteKeys != null
                       )
                   prevPage
               }
               LoadType.APPEND->{
                   val remoteKeys = getRemoteKeyForLastItem(state)
                   val nextPage = remoteKeys?.nextPage
                       ?: return MediatorResult.Success(
                           endOfPaginationReached = remoteKeys != null
                       )
                   nextPage
               }
           }
            val response = watchApi.getAllWatches(page = page)

            if(response.watches.isNotEmpty()){
                watchDatabase.withTransaction {
                    if(loadType == LoadType.REFRESH){
                        watchDao.deleteAllWatches()
                        watchRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.watches.map {watch->
                        WatchRemoteKeys(
                            id = watch.id,
                            prevPage,
                            nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    watchRemoteKeysDao.addAllRemoteKeys(keys)
                    watchDao.addWatches(response.watches)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        }catch (e: Exception){
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = watchRemoteKeysDao.getRemoteKeys(id = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440
        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if(diffInMinutes.toInt() <= cacheTimeout){
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Watch>): WatchRemoteKeys? {
        return state.anchorPosition?.let {position->
            state.closestItemToPosition(position)?.id?.let { id->
                watchRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Watch>): WatchRemoteKeys? {
        return state.pages.firstOrNull{
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let {watch->
            watchRemoteKeysDao.getRemoteKeys(watch.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Watch>): WatchRemoteKeys? {
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()?.let {watch ->
            watchRemoteKeysDao.getRemoteKeys(watch.id)

        }
    }

}