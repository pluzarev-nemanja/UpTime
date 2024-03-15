package com.kumcompany.uptime.domain.use_cases

import com.kumcompany.uptime.domain.use_cases.get_all_watches.GetAllWatchesUseCase
import com.kumcompany.uptime.domain.use_cases.get_selected_watch.GetSelectedWatchUseCase
import com.kumcompany.uptime.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.kumcompany.uptime.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.kumcompany.uptime.domain.use_cases.search_watches.SearchWatchesUseCase

data class UseCases(
    val saveOnBoardingUseCase: SaveOnBoardingUseCase,
    val readOnBoardingUseCase: ReadOnBoardingUseCase,
    val getAllWatchesUseCase: GetAllWatchesUseCase,
    val searchWatchesUseCase: SearchWatchesUseCase,
    val getSelectedWatchUseCase: GetSelectedWatchUseCase
)