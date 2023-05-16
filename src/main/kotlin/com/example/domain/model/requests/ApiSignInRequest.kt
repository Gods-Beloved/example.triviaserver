package com.example.domain.model.requests

@kotlinx.serialization.Serializable
data class ApiSignInRequest(
    val username:String,
    val password:String,
)