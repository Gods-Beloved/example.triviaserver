package com.example.data.repository

import com.example.domain.model.user.TriveaUser
import com.example.domain.repository.UserDataSource
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.util.*

class UserDataSourceImpl(
    database:CoroutineDatabase
):UserDataSource {

    private val users = database.getCollection<TriveaUser>()

    override suspend fun getUserInfo(userId: String): TriveaUser? {
        return users.findOne(filter = TriveaUser::ownerId eq userId)
    }

    override suspend fun verifyUser(email: String): TriveaUser? {
        return users.findOne(filter = TriveaUser::email eq email)
    }

//    override suspend fun verifyCode(userId: String, code: String): Boolean {
//        val triveaUser = users.findOne(TriveaUser::ownerId eq userId)
//
//        if (triveaUser != null) {
//            return  triveaUser.tempCode == code
//        }
//        return false
//    }

    override suspend fun signInUser(username: String):TriveaUser? {
        return users.findOne(or(TriveaUser::username eq username ,TriveaUser::email eq username.lowercase(Locale.getDefault()) ) )
    }


    override suspend fun saveUserInfo(triveaUser:TriveaUser): Boolean {
        val existingTriveaUser = users.findOne(filter = TriveaUser::ownerId eq triveaUser.ownerId)
        return if (existingTriveaUser == null) {
            users.insertOne(document = triveaUser).wasAcknowledged()
        } else {
            false
        }
    }

    override suspend fun saveUserSignUpInfo(triveaUser: TriveaUser): Boolean {

            val existingTriveaUser = users.findOne(filter = TriveaUser::ownerId eq triveaUser.ownerId)
        return   if (existingTriveaUser == null) {
                users.insertOne(document = triveaUser).wasAcknowledged()
            } else {
                true
            }


    }



    override suspend fun deleteUser(userId: String): Boolean {
        return users.deleteOne(filter = TriveaUser::ownerId eq userId).wasAcknowledged()
    }

    override suspend fun updateUserInfo(userId: String,username:String): Boolean {

        return users.updateOne(
            filter = TriveaUser::ownerId eq userId,
            update = setValue(
                property = TriveaUser::username,
                value = username
            )
        ).wasAcknowledged()
    }

    override suspend fun updateUserRef(userId: String, ref: String): Boolean {
        return users.updateOne(
            filter = TriveaUser::ownerId eq userId,
            update = setValue(
                property = TriveaUser::payRef,
                value = ref
            )
        ).wasAcknowledged()
    }

    override suspend fun updateWithdrawRef(userId: String, ref: String): Boolean {
        return users.updateOne(
            filter = TriveaUser::ownerId eq userId,
            update = setValue(
                property = TriveaUser::withdrawRef,
                value = ref
            )
        ).wasAcknowledged()
    }

    override suspend fun updateBalance(userId: String, amount: Double): Boolean {
        return users.updateOne(
            filter = TriveaUser::ownerId eq userId,
            update = setValue(
                property = TriveaUser::balance,
                value = amount
            )

        ).wasAcknowledged()
    }

//    override suspend fun updateSalt(userId: String, hash: String , salt:String): Boolean {
//        return    users.updateMany(
//            filter = TriveaUser::ownerId eq userId,
//            updates = arrayOf(
//                SetTo(
//                    property = TriveaUser::password,
//                    value = hash
//                ),
//                SetTo(
//                    property = TriveaUser::salt,
//                    value = salt
//
//                )
//            )
//
//
//        ).wasAcknowledged()
//
//    }
//
//    override suspend fun updateCode(email: String, code: String):Boolean {
//        return users.updateOne(
//            filter = TriveaUser::email eq email,
//            update = setValue(
//                property = TriveaUser::tempCode,
//                value = code
//            )
//
//        ).wasAcknowledged()
//    }

    override suspend fun updateWinnings(userId: String, amount: Double): Boolean {
        return users.updateOne(
            filter = TriveaUser::ownerId eq userId,
            update = setValue(
                property = TriveaUser::winnings,
                value = amount
            )

        ).wasAcknowledged()
    }

}






