package com.example.domain.model.requests

import kotlinx.serialization.Serializable


@Serializable
data class ApiUserIdRequest(
    val userId:String
)