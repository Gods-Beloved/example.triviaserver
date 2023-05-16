package com.example.plugins

import com.example.domain.repository.AdvertDataSource
import com.example.domain.repository.QuestionsDataSource
import com.example.domain.repository.TransactionsDataSource
import com.example.domain.repository.UserDataSource
import com.example.route.*
import com.example.route.adverts.getAdverts
import com.example.route.questions.*
import com.example.route.transaction.transact
import com.example.route.transaction.getAllTransactionDetails
import com.example.route.transaction.getDepositDetails
import com.example.route.transaction.getWithdrawDetails
import com.example.route.deleteUserRoute
import com.example.route.signOutRoute
import com.example.security.hashing.HashingService
import com.example.security.token.TokenConfig
import com.example.security.token.TokenService
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.java.KoinJavaComponent.inject

fun Application.configureRouting(
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig

) {

    routing {

        val userDataSource:UserDataSource by inject(UserDataSource::class.java)
        val questionsDataSource:QuestionsDataSource by inject(QuestionsDataSource::class.java)
        val transactionsDataSource:TransactionsDataSource by inject(TransactionsDataSource::class.java)
        val advertDataSource:AdvertDataSource by inject(AdvertDataSource::class.java)


        rootRoute()

       signInRoute(userDataSource,hashingService,tokenService,tokenConfig,application)
//
      signUpRoute(userDataSource = userDataSource, hashingService = hashingService, app = application)

        verifyEmailRoute(userDataSource,application)

        signOutRoute()

        verifyCodeRoute(userDataSource = userDataSource,application)

        updatePasswordRoute(hashingService = hashingService, userDataSource = userDataSource,application)

        authenticate()

        getSecreteInfo()

//Questions Routes
        getSportsQuestions(questionsDataSource = questionsDataSource)

        getSportPrice(questionsDataSource = questionsDataSource)

        getLivePrice(questionsDataSource = questionsDataSource)

        getEntertainmentQuestions(questionsDataSource = questionsDataSource)

        getLiveQuestions(questionsDataSource = questionsDataSource)

        getAdverts(advertDataSource = advertDataSource)

        getEntertainmentPrice(questionsDataSource = questionsDataSource)


        getAcademicQuestions(questionsDataSource = questionsDataSource)

        getAcademicPrice(questionsDataSource = questionsDataSource)

        getWithdrawDetails(transactionsDataSource = transactionsDataSource, app = application)

        getDepositDetails(transactionsDataSource = transactionsDataSource, app = application)

        getAllTransactionDetails(transactionsDataSource = transactionsDataSource, app = application)

        deleteUserRoute(application,userDataSource)

        getUserInfoRoute(app = application, userDataSource = userDataSource)

        tokenVerificationRoute(application,userDataSource)

        updateUserRoute(application, userDataSource = userDataSource)

        updateUserRefRoute(application, userDataSource = userDataSource)

        updateWithdrawRefRoute(application, userDataSource = userDataSource)

        createSession(application,userDataSource = userDataSource)

        updateUserBalanceRoute(application, userDataSource = userDataSource)

        updateUserWinningsRoute(application, userDataSource = userDataSource)

        transact(application, transactionsDataSource = transactionsDataSource, userDataSource = userDataSource)

        authorizedRoute()

        unauthorizedRoute()





    }

}
