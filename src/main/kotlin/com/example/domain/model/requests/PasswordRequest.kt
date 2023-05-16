package com.example.domain.model.requests

@kotlinx.serialization.Serializable
data class PasswordRequest(
  val userId:String,
  val  password:String

)
