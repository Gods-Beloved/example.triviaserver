package com.example.domain.model.requests

@kotlinx.serialization.Serializable
data class ApiUserRequest(
    val username:String,
    val profilePicture:String,
    val email:String,
    val phoneNumber:String,
    val ownerId:String
)