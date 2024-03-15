package com.kumcompany.uptime.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kumcompany.uptime.data.remote.WatchApi
import com.kumcompany.uptime.domain.model.Watch

class SearchWatchesSource(
    private val watchApi: WatchApi,
    private val query: String
): PagingSource<Int,Watch>() {
    override fun getRefreshKey(state: PagingState<Int, Watch>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Watch> {
        return try {
            val apiResponse = watchApi.searchWatches(model = query)
            val watches = apiResponse.watches
            if(watches.isNotEmpty()){
                LoadResult.Page(
                    data = watches,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage,
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}