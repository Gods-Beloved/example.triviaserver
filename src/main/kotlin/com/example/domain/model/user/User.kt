package com.example.domain.model.user

import com.example.domain.model.user.account_info.Account
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import org.bson.types.ObjectId


@kotlinx.serialization.Serializable
data class User(

    @SerialName("id")
    val id: String,
    val username:String,
    val salt:String?,
    val profilePicture:String? = null,
    val country:String?= null,
    val phoneNumber:String?= null,
    val bioData:String? = null,
    val payRef:String? = null,
    val password:String?,
    val balance: Double = 0.0,
    val winnings:Double = 0.0,
    val email:String,
)
