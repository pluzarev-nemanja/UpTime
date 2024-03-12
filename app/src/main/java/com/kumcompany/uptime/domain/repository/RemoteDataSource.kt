package com.kumcompany.uptime.domain.repository

import androidx.paging.PagingData
import com.kumcompany.uptime.domain.model.Watch
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllWatches(): Flow<PagingData<Watch>>
    fun searchWatches(query : String): Flow<PagingData<Watch>>
}