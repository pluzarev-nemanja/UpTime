package com.kumcompany.uptime.data.repository

import androidx.paging.PagingData
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.repository.DataStoreOperations
import com.kumcompany.uptime.domain.repository.LocalDataSource
import com.kumcompany.uptime.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val remote: RemoteDataSource,
    private val local : LocalDataSource
) {

    fun getAllWatches():Flow<PagingData<Watch>>{
        return remote.getAllWatches()
    }

    suspend fun getSelectedWatch(watchId: Int): Watch{
        return local.getSelectedWatch(watchId)
    }

    fun searchWatches(query: String):Flow<PagingData<Watch>>{
        return remote.searchWatches(query)
    }
    suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.saveOnBoardingState(completed)
    }
    fun readOnBoardingState():Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }
}