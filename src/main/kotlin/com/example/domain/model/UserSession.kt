package com.example.domain.model

import io.ktor.server.auth.*
import org.litote.kmongo.Id

data class UserSession(
    val id: String,
    val username:String,
    val email:String,
):Principal
