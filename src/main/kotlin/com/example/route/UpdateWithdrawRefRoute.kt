package com.example.route

import com.example.domain.model.*
import com.example.domain.model.response.ApiResponse
import com.example.domain.model.updates.RefUpdate
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Route.updateWithdrawRefRoute(
    app: Application,
    userDataSource: UserDataSource
) {
    authenticate("auth-session") {
        put(EndPoints.UpdateWithdrawRef.path) {
            val userSession = call.principal<UserSession>()
            val userRefUpdate = call.receive<RefUpdate>()
            if (userSession == null) {
                app.log.info("INVALID SESSION")
                call.respondRedirect(EndPoints.Unauthorized.path)
            } else {
                try {
                    updateWithdrawRef(
                        app = app,
                        userId = userSession.id,
                        userRef = userRefUpdate,
                        userDataSource = userDataSource
                    )
                } catch (e: Exception) {
                    app.log.info("UPDATE USER INFO ERROR: $e")
                    call.respondRedirect(EndPoints.Unauthorized.path)
                }
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.updateWithdrawRef(
    app: Application,
    userId: String,
    userRef: RefUpdate,
    userDataSource: UserDataSource
) {
    val response = userDataSource.updateWithdrawRef(
        userId = userId,
       ref = userRef.ref
    )
    if (response) {
        app.log.info("USER SUCCESSFULLY UPDATED")
        call.respond(
            message = ApiResponse(
                success = true,
                message = "Successfully Updated!"
            ),
            status = HttpStatusCode.OK
        )
    } else {
        app.log.info("ERROR UPDATING THE USER")
        call.respond(
            message = ApiResponse(success = false),
            status = HttpStatusCode.BadRequest
        )
    }
}