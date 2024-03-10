package com.kumcompany.uptime.domain.use_cases.save_onboarding

import com.kumcompany.uptime.data.repository.Repository

class SaveOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed)
    }

}