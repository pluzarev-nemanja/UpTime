package com.kumcompany.uptime.di

import android.content.Context
import com.kumcompany.uptime.data.repository.DataStoreOperationsImpl
import com.kumcompany.uptime.data.repository.Repository
import com.kumcompany.uptime.domain.repository.DataStoreOperations
import com.kumcompany.uptime.domain.use_cases.UseCases
import com.kumcompany.uptime.domain.use_cases.get_all_watches.GetAllWatchesUseCase
import com.kumcompany.uptime.domain.use_cases.get_selected_watch.GetSelectedWatchUseCase
import com.kumcompany.uptime.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.kumcompany.uptime.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.kumcompany.uptime.domain.use_cases.search_watches.SearchWatchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ):DataStoreOperations{
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository),
            getAllWatchesUseCase = GetAllWatchesUseCase(repository),
            searchWatchesUseCase = SearchWatchesUseCase(repository),
            getSelectedWatchUseCase = GetSelectedWatchUseCase(repository)
        )
    }
}