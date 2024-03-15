package com.kumcompany.uptime.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kumcompany.uptime.domain.model.Watch
import com.kumcompany.uptime.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedWatches = MutableStateFlow<PagingData<Watch>>(PagingData.empty())
    val searchedWatches = _searchedWatches

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchWatches(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchWatchesUseCase(query = query).cachedIn(viewModelScope).collect{
                _searchedWatches.value = it
            }
        }
    }
}