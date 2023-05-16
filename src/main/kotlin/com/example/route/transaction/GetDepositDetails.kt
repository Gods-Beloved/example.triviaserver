package com.example.route.transaction

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
import com.example.domain.model.response.ApiResponse
import com.example.domain.model.response.TransactionResponse
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getDepositDetails(
    app: Application,
    transactionsDataSource: TransactionsDataSource
) {

    authenticate("auth-session") {

    get(EndPoints.GetUserDeposits.path) {

        val userSession = call.principal<UserSession>()
        if (userSession == null) {
            app.log.info("INVALID SESSION")
            call.respondRedirect(EndPoints.Unauthorized.path)
        } else {
//            try {
//                app.log.info("VALID SESSION ${userSession.id}")

                call.respond(
                    message = TransactionResponse(
                        success = true,
                        //Use the id stored from cookies
                       account  = transactionsDataSource.getOnlyDeposit(userSession.id)
                    ),
                    status = HttpStatusCode.OK
                )
//            } catch (e: Exception) {
//                app.log.info("GETTING USER INFO ERROR: ${e.message}")
//                call.respondRedirect(EndPoints.Unauthorized.path)
//            }
        }
    }

    }

}