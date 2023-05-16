package com.example.route

import com.example.domain.model.*
import com.example.domain.model.requests.ApiTokenRequest
import com.example.domain.model.user.TriveaUser
import com.example.domain.repository.UserDataSource
import com.example.util.Constants.AUDIENCE
import com.example.util.Constants.ISSUER
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.pipeline.*

fun Route.tokenVerificationRoute(
    app: Application,
    userDataSource: UserDataSource
) {
    post(EndPoints.TokenVerification.path) {
        val request = call.receive<ApiTokenRequest>()
        val userSession = call.principal<UserSession>()
      //  val principal = call.principal<JWTPrincipal>()
      //  val userId = principal?.getClaim("userId", String::class)

        if (request.tokenId.isNotEmpty()) {

            app.log.info("Token ID recieved,${request.tokenId}")

           val result = verifyGoogleTokenId(tokenId = request.tokenId,app)

            if (result != null) {

                saveUserToDatabase(
                    app = app,
                    result = result,
                    userDataSource = userDataSource
                )

                    }else{
                app.log.info("TOKEN VERIFICATION NULL AND FAILED")
                call.respondRedirect(EndPoints.Unauthorized.path)
            }

        } else {
            app.log.info("EMPTY TOKEN ID")
            call.respondRedirect(EndPoints.Unauthorized.path)
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.saveUserToDatabase(
    app: Application,
    result: GoogleIdToken,
    userDataSource: UserDataSource
) {

    val name = result.payload["name"].toString()
    val sub = result.payload["sub"].toString()
    val emailAddress = result.payload["email"].toString()
    val profilePhoto = result.payload["picture"].toString()

    val triveaUser = TriveaUser(
        ownerId=sub,
        profilePicture= profilePhoto,
        username = name,
        email = emailAddress,
        bioData = "Trivous App Player",

        phoneNumber = ""
       // account = Account()
    )

  //  call.sessions.clear<UserSession>()

    val response = userDataSource.saveUserSignUpInfo(triveaUser = triveaUser)
    if (response) {
        app.log.info("USER SUCCESSFULLY SAVED")

        call.sessions.set(UserSession(id = sub, username = triveaUser.username, email = triveaUser.email))
        call.respondRedirect(EndPoints.Authorized.path)
    } else {
        app.log.info("ERROR SAVING THE USER")
        call.respondRedirect(EndPoints.Unauthorized.path)
    }
}

fun verifyGoogleTokenId(tokenId: String,app:Application): GoogleIdToken? {



    return try{
    val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(),GsonFactory())
        .setAudience(listOf(AUDIENCE))
        .setIssuer(ISSUER)
        .build()

    verifier.verify(tokenId)
    }catch (e:Exception){
        app.log.info("exception $e")
        null
    }



}