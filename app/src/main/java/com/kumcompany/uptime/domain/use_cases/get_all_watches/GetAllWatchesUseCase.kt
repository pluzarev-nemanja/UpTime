package com.kumcompany.uptime.domain.use_cases.get_all_watches

import androidx.paging.PagingData
import com.kumcompany.uptime.data.repository.Repository
import com.kumcompany.uptime.domain.model.Watch
import kotlinx.coroutines.flow.Flow

class GetAllWatchesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Watch>> {
        return repository.getAllWatches()
    }
}