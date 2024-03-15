package com.kumcompany.uptime.di

import android.content.Context
import androidx.room.Room
import com.kumcompany.uptime.data.local.WatchDatabase
import com.kumcompany.uptime.data.repository.LocalDataSourceImpl
import com.kumcompany.uptime.domain.repository.LocalDataSource
import com.kumcompany.uptime.util.Constants.WATCH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : WatchDatabase{
        return Room.databaseBuilder(
            context = context,
            WatchDatabase::class.java,
            WATCH_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: WatchDatabase
    ): LocalDataSource{
        return LocalDataSourceImpl(
            watchDatabase = database
        )
    }

}