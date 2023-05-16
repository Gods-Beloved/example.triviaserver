package com.example.route.transaction

import com.example.domain.model.EndPoints
import com.example.domain.model.UserSession
//import com.example.domain.model.UserSession
import com.example.domain.model.response.SaveResponse
import com.example.domain.model.user.account_info.Account
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.transact(
    app:Application,
    transactionsDataSource: TransactionsDataSource,
    userDataSource: UserDataSource
) {

    authenticate("auth-session") {

        post(EndPoints.SaveTransaction.path){
           val userSession = call.principal<UserSession>()
           // val principal = call.principal<JWTPrincipal>()
            val userId = userSession?.id



            val request = call.receive<Account>()

            val acc = Account(
                id= request.id,
                email=request.email,
                name = request.name,
                amount = request.amount,
                transferType = request.transferType,
                reference = request.reference,

            )


            if (userId == null) {
                app.log.info("INVALID SESSION")
                call.respondRedirect(EndPoints.Unauthorized.path)
            }else{
                try {

                    val response =  transactionsDataSource.saveTransaction(account = acc,userId = userId,userDataSource = userDataSource,request.amount)

                    if (response) {
                        

                        app.log.info("TRANSACTION SUCCESSFULLY SAVED/RETRIEVED")
                        call.respond(SaveResponse(
                            updated =  true
                        ))
                    } else {
                        app.log.info("ERROR SAVING THE TRANSFER")
                        call.respond(SaveResponse(
                            updated =  false
                        ))

                    }
                }catch (ex:Exception){
                    app.log.info("ERROR SAVING THE TRANSFER $ex")

                }



            }




            }
       }




//    }
}