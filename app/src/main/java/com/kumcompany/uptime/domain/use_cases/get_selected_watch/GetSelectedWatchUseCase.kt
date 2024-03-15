package com.kumcompany.uptime.domain.use_cases.get_selected_watch

import com.kumcompany.uptime.data.repository.Repository
import com.kumcompany.uptime.domain.model.Watch

class GetSelectedWatchUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(watchId: Int): Watch {
        return repository.getSelectedWatch(watchId)
    }
}