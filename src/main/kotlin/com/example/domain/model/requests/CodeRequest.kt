package com.example.domain.model.requests

@kotlinx.serialization.Serializable
data class CodeRequest(
    val userId:String,
    val code:String
)
