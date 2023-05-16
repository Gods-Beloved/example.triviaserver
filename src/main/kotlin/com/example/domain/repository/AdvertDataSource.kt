package com.example.domain.repository

import com.example.domain.model.advertisement.Ads

interface AdvertDataSource {
    suspend fun getAdverts ():List<Ads>?
}