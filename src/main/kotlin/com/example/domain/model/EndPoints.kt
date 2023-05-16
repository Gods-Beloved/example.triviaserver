package com.example.domain.model

sealed class EndPoints (val path:String){
    object Root:EndPoints(path = "/")
    object TokenVerification:EndPoints(path = "/token_verification")
    object GetLiveAds:EndPoints(path = "/get_ads")
    object GetUserInfo:EndPoints(path = "/get_user" )

    object VerifyEmail:EndPoints(path = "/verify_user")
    object GetSportsQuestions:EndPoints(path = "/get_sports_questions" )
    object GetEntertainmentQuestions:EndPoints(path = "/get_entertainment_questions" )
    object GetAcademicQuestions:EndPoints(path = "/get_academic_questions" )
    object GetLiveQuestions:EndPoints(path = "/get_live_questions" )
    object GetLivePrice:EndPoints(path = "/get_price_live" )
    object GetSportPrice:EndPoints(path = "/get_price_sport" )
    object GetEntertainmentPrice:EndPoints(path = "/get_price_entertainment" )
    object GetAcademicPrice:EndPoints(path = "/get_price_academic" )


    
    object SaveTransaction:EndPoints(path = "/save_payments")
    object GetUserWithdrawals:EndPoints(path = "/get_withdrawal")
    object GetUserDeposits:EndPoints(path = "/get_deposits")
    object GetUserTransactions:EndPoints(path = "/get_all_transactions")
    object GetSessionId:EndPoints(path = "/get_session_id")

    object SignUpUser:EndPoints(path = "/signup_user")
    object UpdateUserPassword:EndPoints(path = "/reset_user_password")

    object VerifyUserCode:EndPoints(path = "/verify_user_code")
    object SignInUser:EndPoints(path = "/signin_user")


    object UpdateUserInfo:EndPoints(path = "/update_user")
    object UpdateUserRef:EndPoints(path = "/update_user_ref")
    object UpdateWithdrawRef:EndPoints(path = "/update_withdraw_ref")

    object UpdateUserBalance:EndPoints(path = "/update_user_balance")
    object UpdateUserWinnings:EndPoints(path = "/update_user_winnings")

    object DeleteUser:EndPoints(path = "/delete_user")
    object SignOut:EndPoints(path = "/sign_out")
    object Unauthorized:EndPoints(path = "/unauthorized")
    object NoAvailableQuestion:EndPoints(path = "/unavailable")
    object Authorized:EndPoints(path = "/authorized")


}