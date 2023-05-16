package com.example.route.questions

import com.example.domain.model.EndPoints
import com.example.domain.model.questions.QuestionResponse
import com.example.domain.model.questions.Sports
import com.example.domain.repository.QuestionsDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getSportsQuestions(
    questionsDataSource: QuestionsDataSource
){

    get(EndPoints.GetSportsQuestions.path) {


        val enterQuestions = questionsDataSource.getSportQuestion()

        val updated = questionsDataSource.updateSportQuestion(enterQuestions?.question.toString())

        if (updated){
            call.respond(HttpStatusCode.OK, message = QuestionResponse(
             success = true,
                sports = enterQuestions
            )
            )
        }else{
            call.respond(HttpStatusCode.OK, message = QuestionResponse(
                success = false
            ))
        }









    }


}