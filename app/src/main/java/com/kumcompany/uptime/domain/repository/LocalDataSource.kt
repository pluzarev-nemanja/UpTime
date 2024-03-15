package com.kumcompany.uptime.domain.repository

import com.kumcompany.uptime.domain.model.Watch

interface LocalDataSource {

    suspend fun getSelectedWatch(watchId : Int) : Watch
}