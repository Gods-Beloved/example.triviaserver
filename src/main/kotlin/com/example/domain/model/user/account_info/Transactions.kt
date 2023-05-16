package com.example.domain.model.user.account_info

@kotlinx.serialization.Serializable
data class Transactions(
    val withdrawals:List<Withdrawal>,
    val Deposits:List<Deposits>
)
