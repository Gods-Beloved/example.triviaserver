package com.example.data.repository

import com.example.domain.model.advertisement.Ads
import com.example.domain.model.advertisement.Advert
import com.example.domain.repository.AdvertDataSource
import org.litote.kmongo.coroutine.CoroutineDatabase

class AdvertDataSourceImpl(
    database: CoroutineDatabase

):AdvertDataSource{

    private val adverts = database.getCollection<Advert>()

    override suspend fun getAdverts(): List<Ads>? {

        return adverts.findOne()?.ads

    }
}