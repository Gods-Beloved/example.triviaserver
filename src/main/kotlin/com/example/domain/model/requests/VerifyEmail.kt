package com.example.domain.model.requests

@kotlinx.serialization.Serializable
data class VerifyEmail(
    val email:String,
    val code:String
)
