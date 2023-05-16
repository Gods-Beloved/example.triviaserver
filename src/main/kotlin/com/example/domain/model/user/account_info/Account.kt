package com.example.domain.model.user.account_info

import kotlinx.serialization.Contextual
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetTime

@kotlinx.serialization.Serializable
data class Account(
   val id:String,
   val email:String,
   val name:String,
   val amount:String,
   val transferType:String,
   val reference:String,
//   @Contextual
//   val time:LocalDate = LocalDate.now()

)