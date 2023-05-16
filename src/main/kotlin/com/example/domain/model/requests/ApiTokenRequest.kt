package com.example.domain.model.requests

import kotlinx.serialization.Serializable


@Serializable
data class ApiTokenRequest(
    val tokenId:String
)