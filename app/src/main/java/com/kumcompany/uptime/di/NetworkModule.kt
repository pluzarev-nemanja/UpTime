package com.kumcompany.uptime.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kumcompany.uptime.data.local.WatchDatabase
import com.kumcompany.uptime.data.remote.WatchApi
import com.kumcompany.uptime.data.repository.RemoteDataSourceImpl
import com.kumcompany.uptime.domain.repository.RemoteDataSource
import com.kumcompany.uptime.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit{
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideWatchApi(retrofit: Retrofit):WatchApi{
        return retrofit.create(WatchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        watchApi: WatchApi,
        watchDatabase: WatchDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImpl(
            watchApi, watchDatabase
        )
    }
}