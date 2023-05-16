package com.example.route

import com.example.domain.model.EndPoints
import com.example.domain.model.requests.VerifyEmail
import com.example.domain.model.response.ApiResponse
import com.example.domain.model.user.TriveaUser
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*


fun Route.verifyEmailRoute(
    userDataSource: UserDataSource,
    app: Application
){

    post(EndPoints.VerifyEmail.path){
        val request = call.receiveOrNull<VerifyEmail>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest,"Request is null")
            return@post
        }

        val user = userDataSource.verifyUser(
            request.email
        )

        if (user == null){
            call.respond(HttpStatusCode.Conflict,"user not found")
            return@post
        }else{
            try {
                updateUserCode(
                    app = app,
                    email = request.email,
                    triveaUser = user ,
                    verifyEmail = request,
                    userDataSource = userDataSource
                )


            }catch (e:Exception){
                app.log.info("UPDATE USER INFO ERROR: $e")
                call.respondRedirect(EndPoints.Unauthorized.path)
            }


        }






    }



}
private suspend fun PipelineContext<Unit, ApplicationCall>.updateUserCode(
    app: Application,
    email: String,
    triveaUser: TriveaUser?,
    verifyEmail: VerifyEmail,
    userDataSource: UserDataSource
) {
    val response = true
    if (response) {
        app.log.info("USER SUCCESSFULLY UPDATED")
        call.respond(
            message = ApiResponse(
                success = true,
                message = "USER SUCCESSFULLY UPDATED",
                triveaUser =  triveaUser
            ),
            status = HttpStatusCode.OK
        )
    } else {
        app.log.info("ERROR UPDATING THE CODE")
        call.respond(
            message = ApiResponse(success = false),
            status = HttpStatusCode.BadRequest
        )
    }
}