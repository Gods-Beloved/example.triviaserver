package com.example.route

import com.example.domain.model.*
import com.example.domain.model.response.ApiResponse
import com.example.domain.model.updates.BalanceUpdate
import com.example.domain.model.updates.UserUpdate
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Route.updateUserBalanceRoute(
    app: Application,
    userDataSource: UserDataSource
) {
    authenticate("auth-session") {
        put(EndPoints.UpdateUserBalance.path) {
           val userSession = call.principal<UserSession>()
            val userId = userSession?.id
            val balanceUpdate = call.receive<BalanceUpdate>()
            if (userId == null) {
                app.log.info("INVALID SESSION")
                call.respondRedirect(EndPoints.Unauthorized.path)
            } else {
                try {
                    updateUserBalance(
                        app = app,
                        userId = userId,
                        balanceUpdate = balanceUpdate,
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

private suspend fun PipelineContext<Unit, ApplicationCall>.updateUserBalance(
    app: Application,
    userId: String,
    balanceUpdate: BalanceUpdate,
    userDataSource: UserDataSource
) {
    val response = userDataSource.updateBalance(
        userId = userId,
        amount = balanceUpdate.amount
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