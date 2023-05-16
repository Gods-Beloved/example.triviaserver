package com.example.domain.model.user

import kotlinx.serialization.SerialName
import org.bson.types.ObjectId


@kotlinx.serialization.Serializable
data class TriveaUser(


    val ownerId: String,
    val username:String,
    val profilePicture:String? = null,
    val phoneNumber:String,
    val bioData:String,
    val payRef:String? = null,
    val withdrawRef:String? = null,
    val balance: Double = 0.0,
    val winnings:Double = 0.0,
    val email:String="",
)
