package com.example.domain.repository

import com.example.domain.model.questions.*
import org.litote.kmongo.coroutine.CoroutineDistinctPublisher

interface QuestionsDataSource {
    suspend fun getSportQuestion(): Sports?
    suspend fun updateSportQuestion(question: String): Boolean


    suspend fun getEntertainmentQuestion(): Entertainment?
    suspend fun updateEntertainmentQuestion(question: String): Boolean


    suspend fun getAcademicQuestion(): Academic?
    suspend fun updateAcademicQuestion(question: String): Boolean

    suspend fun getLiveQuestion(): General?
    suspend fun updateLiveQuestion(question: String): Boolean


    suspend fun getSportPrice(): FeeSizeResponse?
    suspend fun getEntertainmentPrice(): FeeSizeResponse?
    suspend fun getAcademicPrice(): FeeSizeResponse?
    suspend fun getLivePrice(): FeeSizeResponse?


    //suspend fun updateReceivedQuestion():Boolean

}