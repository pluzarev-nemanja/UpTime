package com.kumcompany.uptime.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.kumcompany.uptime.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    val getAllWatches = useCases.getAllWatchesUseCase()

}