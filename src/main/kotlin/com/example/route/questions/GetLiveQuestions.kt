package com.example.route.questions

import com.example.domain.model.EndPoints
import com.example.domain.model.questions.QuestionResponse
import com.example.domain.repository.QuestionsDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getLiveQuestions(
    questionsDataSource: QuestionsDataSource
){

        get(EndPoints.GetLiveQuestions.path) {


            val enterQuestions = questionsDataSource.getLiveQuestion()

            val updated = questionsDataSource.updateLiveQuestion(enterQuestions?.question.toString())

            if (updated){
                call.respond(HttpStatusCode.OK, message = QuestionResponse(
                    success = true,
                    general = enterQuestions
                )
                )
            }else{
                call.respond(HttpStatusCode.OK, message = QuestionResponse(
                    success = false
                ))
            }




        }


}