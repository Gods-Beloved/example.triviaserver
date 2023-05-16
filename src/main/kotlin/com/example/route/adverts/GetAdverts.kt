package com.example.route.adverts

import com.example.domain.model.EndPoints
import com.example.domain.model.advertisement.AdResponse
import com.example.domain.model.questions.QuestionResponse
import com.example.domain.repository.AdvertDataSource
import com.example.domain.repository.QuestionsDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getAdverts(
    advertDataSource: AdvertDataSource
){

        get(EndPoints.GetLiveAds.path) {


            val adverts = advertDataSource.getAdverts()




            if (adverts != null){
                call.respond(HttpStatusCode.OK, message = AdResponse(
                    success = true,
                    ads = adverts
                )
                )
            }else{
                call.respond(HttpStatusCode.OK, message = AdResponse(
                    success = false
                ))
            }




        }


}