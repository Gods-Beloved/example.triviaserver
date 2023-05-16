package com.example.domain.model.user.account_info

@kotlinx.serialization.Serializable
data class Withdrawal(
    val agentName:String?=null,
    val amount:Int=0,
)
