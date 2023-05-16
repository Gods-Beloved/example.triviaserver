package com.example.route.questions

import com.example.domain.model.EndPoints
import com.example.domain.model.questions.QuestionResponse
import com.example.domain.repository.QuestionsDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getEntertainmentQuestions(
    questionsDataSource: QuestionsDataSource
){

        get(EndPoints.GetEntertainmentQuestions.path) {


            val enterQuestions = questionsDataSource.getEntertainmentQuestion()

            val updated = questionsDataSource.updateEntertainmentQuestion(enterQuestions?.question.toString())

            if (updated){
                call.respond(HttpStatusCode.OK, message = QuestionResponse(
                    success = true,
                    entertainment = enterQuestions
                )
                )
            }else{
                call.respond(HttpStatusCode.OK, message = QuestionResponse(
                    success = false
                ))
            }

        }


}