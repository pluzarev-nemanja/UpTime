package com.kumcompany.uptime.domain.use_cases.search_watches

import androidx.paging.PagingData
import com.kumcompany.uptime.data.repository.Repository
import com.kumcompany.uptime.domain.model.Watch
import kotlinx.coroutines.flow.Flow

class SearchWatchesUseCase(
    private val repository: Repository
) {

    operator fun invoke(query: String):Flow<PagingData<Watch>>{
        return repository.searchWatches(query)
    }
}