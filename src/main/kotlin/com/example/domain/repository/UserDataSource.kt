package com.example.domain.repository

import com.example.domain.model.user.TriveaUser

interface UserDataSource {


       suspend fun getUserInfo(userId:String): TriveaUser?

       suspend fun verifyUser(email: String):TriveaUser?




       suspend fun signInUser(username:String):TriveaUser?

       suspend fun saveUserInfo(triveaUser: TriveaUser): Boolean

       suspend fun saveUserSignUpInfo(triveaUser: TriveaUser): Boolean



       suspend fun deleteUser(userId: String): Boolean

       suspend fun updateUserInfo(
           userId: String,
           username:String
       ): Boolean

       suspend fun updateUserRef(
           userId: String,
           ref:String
       ): Boolean

    suspend fun updateWithdrawRef(
        userId: String,
        ref:String
    ): Boolean


    suspend fun updateBalance(
           userId: String,
           amount:Double
       ): Boolean
//    suspend fun updateSalt(
//           userId: String,
//           hash:String,
//           salt:String
//       ): Boolean

//        suspend fun verifyCode(
//           userId: String,
//           code:String
//       ): Boolean



//    suspend fun updateCode(
//        email: String,
//        code:String
//    ):Boolean

       suspend fun updateWinnings(
           userId: String,
           amount:Double
       ): Boolean



}