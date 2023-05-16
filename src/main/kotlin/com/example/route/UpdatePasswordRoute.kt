package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.requests.PasswordRequest
import com.example.domain.repository.UserDataSource
import com.example.security.hashing.HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.updatePasswordRoute(

    hashingService: HashingService,
    userDataSource: UserDataSource,
    app:Application
) {


    post(EndPoints.UpdateUserPassword.path) {


        val request = call.receiveOrNull<PasswordRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request is null")
            return@post
        }




//        val areFieldsBlank = request.username.isBlank() || request.password.isBlank()
//        val isPwTooShort = request.password.length < 8
//
//        if (areFieldsBlank || isPwTooShort) {
//            call.respond(HttpStatusCode.Conflict)
//            return@post
//        }

        val saltedHash = hashingService.generateSaltedHash(request.password, application = application)




        val wasAcknowledged = true

        if (!wasAcknowledged) {
            call.respond(HttpStatusCode.Conflict,"USER ALREADY EXIST")
            return@post
        }



        call.respondRedirect(EndPoints.Authorized.path)
//        call.respond(
//            ApiResponse(
//                success = true,
//                user = user
//            )
//        )



    }


}