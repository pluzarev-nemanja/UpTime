package com.kumcompany.uptime.data.remote

import com.kumcompany.uptime.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WatchApi {

    @GET("/uptime/watches")
    suspend fun getAllWatches(
        @Query("page") page: Int = 1
    ):ApiResponse

    @GET("/uptime/watches/search")
    suspend fun searchWatches(
        @Query("model") model: String
    ):ApiResponse


}